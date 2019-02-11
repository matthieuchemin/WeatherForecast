package chemin.matthieu.repositories

import chemin.matthieu.domain.FavoredLocation
import chemin.matthieu.domain.ReadFavoredLocation
import chemin.matthieu.domain.SearchLocation
import chemin.matthieu.entities.Location


class LocationRepository(private val localLocationDataStore: LocalFavoredLocationDataStore) :
        ReadFavoredLocation.FavoredLocationRepository,
        SearchLocation.LocationRepository,
        FavoredLocation.LocationRepository {

    interface LocalFavoredLocationDataStore {
        fun getFavoredLocations(): List<Location>
        fun searchFavoredLocations(search: String): List<Location>
        fun favoredLocation(locationId: Long, favored: Boolean)
    }

    override fun getFavoredLocations(): List<Location> = localLocationDataStore.getFavoredLocations()

    override fun searchLocation(search: String) = localLocationDataStore.searchFavoredLocations(search)

    override fun favoredLocation(locationId: Long, favored: Boolean) =
            localLocationDataStore.favoredLocation(locationId, favored)
}
