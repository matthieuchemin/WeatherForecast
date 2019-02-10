package chemin.matthieu.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import chemin.matthieu.domain.ReadForecastForPosition
import chemin.matthieu.entities.Forecast
import chemin.matthieu.presentation.model.DisplayForecast
import chemin.matthieu.presentation.model.DisplayForecastMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ForecastViewModel(
        private val readForcastForPosition: ReadForecastForPosition,
        private val displayForecastMapper: DisplayForecastMapper
) : ScopedViewModel() {

    private val _forecast = MutableLiveData<DisplayForecast>()
    val forecast: LiveData<DisplayForecast>
        get() = _forecast

    private var idLocation : Long = 0

    fun setLocationId(idLocation: Long) {
        this.idLocation = idLocation
    }

    fun refreshForecast() {
        launch {
            val forecast = withContext(Dispatchers.IO) {
                readForcastForPosition.perform(idLocation)
            }
            val displayForecast = withContext(Dispatchers.Default) {
                displayForecastMapper.map(forecast)
            }
            _forecast.value = displayForecast
        }
    }
}