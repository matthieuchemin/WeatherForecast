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

    private lateinit var forecastViewModel: ForecastViewModel

    private lateinit var forecastDateTextView: TextView
    private lateinit var forecastWeatherTextView: TextView
    private lateinit var forecastDetailTextView: TextView
    private lateinit var forecastTemperatureTextView: TextView
    private lateinit var forecastTemperatureMinTextView: TextView
    private lateinit var forecastTemperatureMaxTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_forecast)
        forecastDateTextView = findViewById(R.id.activity_forecast_date)
        forecastWeatherTextView = findViewById(R.id.activity_forecast_weather)
        forecastDetailTextView = findViewById(R.id.activity_forecast_details)
        forecastTemperatureTextView = findViewById(R.id.activity_forecast_temperature)
        forecastTemperatureMinTextView = findViewById(R.id.activity_forecast_temperature_min)
        forecastTemperatureMaxTextView = findViewById(R.id.activity_forecast_temperature_max)

        forecastViewModel = ViewModelProviders.of(this, viewModelFactory).get(ForecastViewModel::class.java)
        val locationId = intent.getLongExtra(LOCATION_ID, 6454573)
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