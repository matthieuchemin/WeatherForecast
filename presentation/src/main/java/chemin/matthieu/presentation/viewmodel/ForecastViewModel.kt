package chemin.matthieu.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import chemin.matthieu.commontools.d
import chemin.matthieu.domain.ReadForecastForPosition
import chemin.matthieu.domain.ReadLocationName
import chemin.matthieu.entities.Forecast
import chemin.matthieu.presentation.model.DisplayForecast
import chemin.matthieu.presentation.model.DisplayForecastMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

private const val TAG = "ForecastViewModel"

class ForecastViewModel(
        private val readForcastForPosition: ReadForecastForPosition,
        private val readLocationName: ReadLocationName,
        private val displayForecastMapper: DisplayForecastMapper
) : ScopedViewModel() {

    private val _forecast = MutableLiveData<DisplayForecast>()
    val forecast: LiveData<DisplayForecast>
        get() = _forecast

    private val _locationName = MutableLiveData<String>()
    val locationName: LiveData<String>
        get() = _locationName

    private var idLocation: Long = 0

    fun setLocationId(idLocation: Long) {
        this.idLocation = idLocation
        launch {
            val name = withContext(Dispatchers.IO) {
                readLocationName.perform(idLocation)
            }
            _locationName.value = name
        }
    }

    fun refreshForecast() {
        launch {
            val location = idLocation
            val forecast = withContext(Dispatchers.IO) {
                readForcastForPosition.perform(location)
            }
            if (forecast != null) {
                val displayForecast = withContext(Dispatchers.Default) {
                    displayForecastMapper.map(forecast)
                }
                _forecast.value = displayForecast
            } else {
                Timber.d(TAG, "failed to read forecast for position $location")
            }
        }
    }
}