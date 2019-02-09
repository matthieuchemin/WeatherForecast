package chemin.matthieu.domain

import chemin.matthieu.entities.Forecast
import java.util.concurrent.TimeUnit

class ReadForecastForPosition(
        private val forecastRepository: ForecastRepository,
        private val taskScheduler: TaskScheduler
) : UseCase<Long, Forecast?>() {

    interface ForecastRepository {
        fun forecastForLocation(locationId: Long): Result<Forecast>
    }

    interface TaskScheduler {
        fun scheduleNextExecution(delayInMillisecond: Long)
    }

    override suspend fun perform(input: Long): Forecast? {
        val result = forecastRepository.forecastForLocation(input)
        return when (result) {
            is Success -> {
                // we have data, the next execution will be in 6 hours
                taskScheduler.scheduleNextExecution(SIX_HOURS)
                result.data
            }
            is Failure -> {
                // we have no data, the next execution will be in 15 minutes
                taskScheduler.scheduleNextExecution(FIFTEEN_MINUTES)
                null
            }
        }
    }

    private companion object {
        val SIX_HOURS = TimeUnit.HOURS.toMillis(6)
        val FIFTEEN_MINUTES = TimeUnit.MINUTES.toMillis(15)
    }
}