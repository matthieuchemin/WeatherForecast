package chemin.matthieu.repositories

import chemin.matthieu.commontools.e
import chemin.matthieu.domain.Failure
import chemin.matthieu.domain.ReadForecastForPosition
import chemin.matthieu.domain.Result
import chemin.matthieu.domain.Success
import chemin.matthieu.entities.Forecast
import timber.log.Timber


private const val TAG = "ForecastRepository"

class ForecastRepository(
        private val localLocationDataStore: LocalLocationDataStore,
        private val remoteForecastDataStore: RemoteForecastDataStore,
        private val localForecastDataStore: LocalForecastDataStore
) : ReadForecastForPosition.ForecastRepository {

    interface LocalLocationDataStore {
        fun getLocationCityNameWithId(locationId: Long): String?
    }

    interface RemoteForecastDataStore {
        fun getForecastForLocationId(locationId: Long): Array<Forecast>
    }

    interface LocalForecastDataStore {
        fun saveForecastForLocationId(locationId: Long, forecastArray: Array<Forecast>)
        fun getForecastForLocationId(locationId: Long): Forecast?
    }

    override fun forecastForLocation(locationId: Long): Result<Forecast> {
        return try {
            val cityName = localLocationDataStore.getLocationCityNameWithId(locationId)
                    ?: throw IllegalArgumentException("unknown location id")
            val forecastArray = remoteForecastDataStore.getForecastForLocationId(locationId)
            if (forecastArray.isNotEmpty()) {
                localForecastDataStore.saveForecastForLocationId(locationId, forecastArray)
            }
            val forecast = localForecastDataStore.getForecastForLocationId(locationId)
            if (forecast != null) {
                Success(forecast)
            } else {
                Failure()
            }
        } catch (e: Exception) {
            Timber.e(TAG, e)
            Failure()
        }
    }
}