package chemin.matthieu.networking.datastore

import chemin.matthieu.commontools.e
import chemin.matthieu.entities.Forecast
import chemin.matthieu.networking.retrofit.OpenWheatherMapRetrofitService
import chemin.matthieu.repositories.ForecastRepository
import timber.log.Timber

private const val TAG = "RemoteForecastDataStore"

class RemoteForecastDataStore(private val openWeatherMapRetrofitService: OpenWheatherMapRetrofitService) : ForecastRepository.RemoteForecastDataStore {

    override fun getForecastForLocationId(locationId: Long): Array<Forecast> {
        try {
            val call = openWeatherMapRetrofitService.getForecast(locationId)
            val response = call.execute()
            return if (response.isSuccessful) {
                val responseBody = response.body()
                responseBody?.let { listForecastRepresentation ->
                    val listForecast = listForecastRepresentation.list.map { forecastRepresentation ->
                        Forecast(
                                timeStamp = forecastRepresentation.timestamp,
                                weather = forecastRepresentation.weather[0].main,
                                weatherDescription = forecastRepresentation.weather[0].description,
                                temperature = forecastRepresentation.mainForecastRepresentation.temperature.toInt(),
                                temperatureMin = forecastRepresentation.mainForecastRepresentation.temperatureMin.toInt(),
                                temperatureMax = forecastRepresentation.mainForecastRepresentation.temperatureMax.toInt()
                        )
                    }
                    listForecast.toTypedArray()
                } ?: emptyArray()
            } else {
                emptyArray()
            }
        } catch (exception: Exception) {
            // this occurs if we have connectivity problem (no network, missing authorization, etc)
            // fot simplicity we handle them all the same
            Timber.e(TAG, exception)
            return emptyArray()
        }
    }
}