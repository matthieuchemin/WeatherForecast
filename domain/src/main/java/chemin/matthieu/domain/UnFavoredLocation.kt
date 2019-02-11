package chemin.matthieu.domain

import chemin.matthieu.entities.Location

class UnFavoredLocation(private val locationRepository: LocationRepository) : UseCase<Pair<Long, String>, List<Location>>() {

    interface LocationRepository {
        fun favoredLocation(locationId: Long, favored: Boolean)
        fun searchLocation(search: String): List<Location>
    }

    override fun perform(input: Pair<Long, String>): List<Location> {
        locationRepository.favoredLocation(input.first, false)
        return locationRepository.searchLocation(input.second)
    }
}