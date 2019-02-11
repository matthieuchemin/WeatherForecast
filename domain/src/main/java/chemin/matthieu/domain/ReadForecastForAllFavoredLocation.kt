package chemin.matthieu.domain

import chemin.matthieu.entities.Forecast
import chemin.matthieu.entities.ForecastOverview
import chemin.matthieu.entities.Location

class ReadForecastForAllFavoredLocation(
        private val locationRepository: LocationRepository,
        private val forecastRepository: ForecastRepository
) : UseCase<Any, List<ForecastOverview>>() {

    interface LocationRepository {
        fun getFavoredLocations(): List<Location>
        fun readLocationName(locationId: Long): String
    }

    interface ForecastRepository {
        fun readForecastForLocation(locationId: Long): Forecast?
    }

    override fun perform(input: Any): List<ForecastOverview> {
        val locations = locationRepository.getFavoredLocations()
        return locations.mapNotNull {
            val locationName = locationRepository.readLocationName(it.id)
            val forecast = forecastRepository.readForecastForLocation(it.id)
            if (forecast != null) {
                ForecastOverview(
                        locationId = it.id,
                        locationName = locationName,
                        timeStamp = forecast.timeStamp,
                        weather = forecast.weather,
                        temperature = forecast.temperature
                )
            } else {
                null
            }
        }
    }
}