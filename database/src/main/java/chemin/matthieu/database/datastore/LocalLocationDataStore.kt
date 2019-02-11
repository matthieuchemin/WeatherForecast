package chemin.matthieu.database.datastore

import chemin.matthieu.database.dao.LocationDao
import chemin.matthieu.database.location.LocationWriter
import chemin.matthieu.entities.Location
import chemin.matthieu.repositories.LocationRepository

class LocalLocationDataStore(
        private val locationDao: LocationDao,
        private val locationWriter: LocationWriter
) : LocationRepository.LocalFavoredLocationDataStore {
    override fun searchFavoredLocations(search: String): List<Location> {
        if (locationDao.countLocation() == 0) {
            locationWriter.writeLocationToDatabase()
        }
        return locationDao.searchLocation("%$search%").map {
            Location(
                    id = it.id,
                    name = it.cityName ?: "-",
                    country = it.country ?: "-",
                    favored = it.favored
            )
        }
    }

    override fun getFavoredLocations() = locationDao.getFavoredLocation().map {
        Location(
                id = it.id,
                name = it.cityName ?: "-",
                country = it.country ?: "-",
                favored = it.favored
        )
    }
}