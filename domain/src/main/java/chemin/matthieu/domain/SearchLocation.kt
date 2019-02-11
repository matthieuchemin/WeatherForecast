package chemin.matthieu.domain

import chemin.matthieu.entities.Location

class SearchLocation(private val locationRepository: LocationRepository) : UseCase<String, List<Location>>() {

    interface LocationRepository {
        fun searchLocation(search: String): List<Location>
    }

    override fun perform(input: String) = locationRepository.searchLocation(input)
}