package chemin.matthieu.weatherforecast.tools

import chemin.matthieu.repositories.ForecastRepository
import java.util.concurrent.TimeUnit

class TimestampBuilder : ForecastRepository.CurrentTimeStampBuilder {

    override fun buildTimeStampOfTheCurrentDay(): Long {
        return System.currentTimeMillis() - TimeUnit.HOURS.toMillis(3)
    }
}