package chemin.matthieu.domain

import chemin.matthieu.entities.Location

class UnFavoredLocationFromFavored(private val locationRepository: LocationRepository) : UseCase<Long, List<Location>>() {

    interface LocationRepository {
        fun favoredLocation(locationId: Long, favored: Boolean)
        fun getFavoredLocations(): List<Location>
    }

    override fun perform(input: Long): List<Location> {
        locationRepository.favoredLocation(input, false)
        return locationRepository.getFavoredLocations()
    }
}