package chemin.matthieu.repositories

import chemin.matthieu.domain.ReadFavoredLocation
import chemin.matthieu.domain.SearchLocation
import chemin.matthieu.entities.Location


class LocationRepository(private val localLocationDataStore: LocalFavoredLocationDataStore)
    : ReadFavoredLocation.FavoredLocationRepository, SearchLocation.LocationRepository {

    interface LocalFavoredLocationDataStore {
        fun getFavoredLocations(): List<Location>
        fun searchFavoredLocations(search: String): List<Location>
    }

    override fun getFavoredLocations(): List<Location> = localLocationDataStore.getFavoredLocations()

    override fun searchLocation(search: String) = localLocationDataStore.searchFavoredLocations(search)
}
