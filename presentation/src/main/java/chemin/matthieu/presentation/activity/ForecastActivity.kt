package chemin.matthieu.presentation.activity

import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import chemin.matthieu.presentation.BuildConfig
import chemin.matthieu.presentation.R
import chemin.matthieu.presentation.model.DisplayForecast
import chemin.matthieu.presentation.viewmodel.ForecastViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class ForecastActivity : FragmentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var forecastViewModel: ForecastViewModel

    lateinit var forecastDateTextView: TextView
    lateinit var forecastWeatherTextView: TextView
    lateinit var forecastDetailTextView: TextView
    lateinit var forecastTemperatureTextView: TextView
    lateinit var forecastTemperatureMinTextView: TextView
    lateinit var forecastTemperatureMaxTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_forecast)

        forecastViewModel = ViewModelProviders.of(this, viewModelFactory).get(ForecastViewModel::class.java)
        val locationId = intent.extras?.getLong(LOCATION_ID) ?: throw IllegalStateException("you should start forecast activity with a param $LOCATION_ID")
        forecastViewModel.setLocationId(locationId)

        if (savedInstanceState == null) {
            // first time we are here, request data refresh
            forecastViewModel.refreshForecast()
        }

        forecastViewModel.forecast.observe(this, Observer {
            showForecast(it)
        })
    }

    private fun showForecast(displayForecast: DisplayForecast) {
        forecastDateTextView.text = displayForecast.readableDate
        forecastWeatherTextView.text = displayForecast.weather
        forecastDetailTextView.text = displayForecast.detail
        forecastTemperatureTextView.text = displayForecast.temperature
        forecastTemperatureMinTextView.text = displayForecast.temperatureMin
        forecastTemperatureMaxTextView.text = displayForecast.temperatureMax
    }

    internal companion object {
        const val LOCATION_ID = "${BuildConfig.APPLICATION_ID}:arg:location_id"
    }
}