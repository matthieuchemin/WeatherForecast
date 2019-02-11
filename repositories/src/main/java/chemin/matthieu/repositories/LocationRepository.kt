package chemin.matthieu.repositories

import chemin.matthieu.domain.*
import chemin.matthieu.entities.Location


class LocationRepository(private val localLocationDataStore: LocalFavoredLocationDataStore) :
        ReadFavoredLocation.FavoredLocationRepository,
        SearchLocation.LocationRepository,
        FavoredLocation.LocationRepository,
        UnFavoredLocationFromSearch.LocationRepository,
        UnFavoredLocationFromFavored.LocationRepository,
        ReadLocationName.LocationRepository
{
    interface LocalFavoredLocationDataStore {
        fun getFavoredLocations(): List<Location>
        fun searchFavoredLocations(search: String): List<Location>
        fun favoredLocation(locationId: Long, favored: Boolean)
        fun getLocationName(locationId: Long): String
    }

    override fun getFavoredLocations(): List<Location> = localLocationDataStore.getFavoredLocations()

    override fun searchLocation(search: String) = localLocationDataStore.searchFavoredLocations(search)

    override fun favoredLocation(locationId: Long, favored: Boolean) =
            localLocationDataStore.favoredLocation(locationId, favored)

    override fun readLocationName(locationId: Long) = localLocationDataStore.getLocationName(locationId)
}
