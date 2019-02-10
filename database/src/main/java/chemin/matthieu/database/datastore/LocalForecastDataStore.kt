package chemin.matthieu.database.datastore

import chemin.matthieu.database.dao.ForecastDao
import chemin.matthieu.database.entities.ForecastDatabaseEntity
import chemin.matthieu.entities.Forecast
import chemin.matthieu.repositories.ForecastRepository

class LocalForecastDataStore(private val forecastDao: ForecastDao) : ForecastRepository.LocalForecastDataStore {

    override fun saveForecastForLocationId(locationId: Long, forecastArray: Array<Forecast>) {
        val forecastDatabaseEntityList = forecastArray.map {
            ForecastDatabaseEntity(
                    id = it.timeStamp,
                    idLocation = locationId,
                    timestamp = it.timeStamp,
                    weather = it.weather,
                    description = it.weatherDescription,
                    temperature = it.temperature,
                    temperatureMin = it.temperatureMin,
                    temperatureMax = it.temperatureMax
            )
        }
        forecastDao.clearAndWriteForecast(locationId, forecastDatabaseEntityList)
    }

    override fun getForecastForLocationId(locationId: Long, afterTimeStamp: Long): Forecast? =
            forecastDao.getForecastFromDb(locationId, afterTimeStamp)?.let {
                Forecast(
                        timeStamp = it.timestamp,
                        weather = it.weather,
                        weatherDescription = it.description,
                        temperature = it.temperature,
                        temperatureMin = it.temperatureMin,
                        temperatureMax = it.temperatureMax
                )
            }
}