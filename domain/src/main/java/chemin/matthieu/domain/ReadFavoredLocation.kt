package chemin.matthieu.domain

import chemin.matthieu.entities.Location

class ReadFavoredLocation(private val favoredLocationRepository: FavoredLocationRepository): UseCase<Any, List<Location>>() {

    interface FavoredLocationRepository {
        fun getFavoredLocations(): List<Location>
    }

    override fun perform(input: Any): List<Location> = favoredLocationRepository.getFavoredLocations()
}