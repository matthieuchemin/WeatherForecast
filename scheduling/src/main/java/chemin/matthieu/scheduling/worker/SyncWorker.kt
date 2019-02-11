package chemin.matthieu.scheduling.worker

import android.content.Context
import androidx.work.Worker
//import androidx.work.WorkerParameters
import chemin.matthieu.commontools.e
import chemin.matthieu.commontools.i
import chemin.matthieu.domain.SyncForecastForFavoredLocation
import timber.log.Timber

private const val TAG = "SyncWorker"

class SyncWorker(
//        context: Context,
//        params: WorkerParameters,
        private val syncForecastForFavoredLocation: SyncForecastForFavoredLocation
) : Worker() {

    override fun doWork(): Result {
        val success = syncForecastForFavoredLocation.perform(0)
        if (success) {
            Timber.i(TAG, "successfully sync all favored location")
            return Result.SUCCESS
        } else {
            Timber.e(TAG, "fail to sync forecast for favored location !!")
            return Result.FAILURE
        }
    }
}