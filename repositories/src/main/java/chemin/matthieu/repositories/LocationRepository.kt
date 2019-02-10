package chemin.matthieu.repositories

import chemin.matthieu.domain.ReadFavoredLocation
import chemin.matthieu.entities.Location


class LocationRepository(private val localLocationDataStore: LocalFavoredLocationDataStore) : ReadFavoredLocation.FavoredLocationRepository {

    interface LocalFavoredLocationDataStore {
        fun getFavoredLocations(): List<Location>
    }

    override fun getFavoredLocations(): List<Location> = localLocationDataStore.getFavoredLocations()
}
