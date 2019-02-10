package chemin.matthieu.database.datastore

import chemin.matthieu.database.dao.LocationDao
import chemin.matthieu.repositories.ForecastRepository

class LocalLocationDataStore(private val locationDao: LocationDao) : ForecastRepository.LocalLocationDataStore {

    override fun getLocationCityNameWithId(locationId: Long): String? =
        locationDao.getLocationCityNameWithId(locationId)
}