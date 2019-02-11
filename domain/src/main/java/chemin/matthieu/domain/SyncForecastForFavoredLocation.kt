package chemin.matthieu.domain

import chemin.matthieu.commontools.d
import chemin.matthieu.commontools.e
import timber.log.Timber
import java.lang.Exception

private const val TAG = "SyncForecastForFavoredLocation"

class SyncForecastForFavoredLocation(
        private val favoredLocation: ReadFavoredLocation,
        private val forecastForPosition: ReadForecastForPosition
) : UseCase<Any, Boolean>() {

    override fun perform(input: Any): Boolean =
            try {
                val locations = favoredLocation.perform(0)
                locations.forEach {
                    Timber.d(TAG, "trying to sync forecast for ${it.name} (id = ${it.id})")
                    forecastForPosition.perform(it.id)
                    Timber.d(TAG, "sync success")
                }
                true
            } catch (e: Exception) {
                Timber.e(TAG, e)
                false
            }
}