package chemin.matthieu.domain

class ReadLocationName(private val locationRepository: LocationRepository) : UseCase<Long, String>() {

    interface LocationRepository {
        fun readLocationName(locationId: Long): String
    }

    override fun perform(input: Long) = locationRepository.readLocationName(input)
}