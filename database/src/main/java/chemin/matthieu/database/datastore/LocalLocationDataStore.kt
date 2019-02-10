package chemin.matthieu.database.datastore

import chemin.matthieu.database.dao.LocationDao
import chemin.matthieu.entities.Location
import chemin.matthieu.repositories.LocationRepository

class LocalLocationDataStore(private val locationDao: LocationDao) : LocationRepository.LocalFavoredLocationDataStore {

    override fun getFavoredLocations() = locationDao.getFavoredLocation().map {
        Location(
                id = it.id,
                name = it.cityName ?: "-",
                country = it.country ?: "-",
                favored = it.favored
        )
    }
}