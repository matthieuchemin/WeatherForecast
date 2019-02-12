package chemin.matthieu.domain

import chemin.matthieu.entities.Forecast

class ReadForecastForPosition(private val forecastRepository: ForecastRepository) : UseCase<Long, Forecast?>() {

    interface ForecastRepository {
        fun forecastForLocation(locationId: Long): Result<Forecast>
    }

    override fun perform(input: Long): Forecast? {
        val result = forecastRepository.forecastForLocation(input)
        return when (result) {
            is Success -> {
                result.data
            }
            is Failure -> {
                null
            }
        }
    }
}