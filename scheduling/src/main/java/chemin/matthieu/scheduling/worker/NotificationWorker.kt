package chemin.matthieu.scheduling.worker

import android.app.Notification
import android.content.Context
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import chemin.matthieu.commontools.e
import chemin.matthieu.domain.ReadForecastForAllFavoredLocation
import chemin.matthieu.entities.ForecastOverview
import chemin.matthieu.scheduling.scheduler.Scheduler
import timber.log.Timber

private const val TAG = "NotificationWorker"

class NotificationWorker(
        context: Context,
        workerParameters: WorkerParameters,
        private val scheduler: Scheduler,
        private val readForecastForAllFavoredLocation: ReadForecastForAllFavoredLocation,
        private val notificationManager: NotificationManagerCompat,
        private val notificationBuilder: NotificationBuilder
) : Worker(context, workerParameters) {

    interface NotificationBuilder {
        fun build(overview: ForecastOverview): Notification?
    }

    override fun doWork(): Result =
            try {
                val forecastOverviews = readForecastForAllFavoredLocation.perform(0)
                forecastOverviews.forEach {
                    val notification = notificationBuilder.build(it)
                    if (notification != null) {
                        notificationManager.notify(it.locationId.toInt(), notification)
                    }
                }
                scheduler.scheduleNotifications()
                Result.success()
            } catch (e: Exception) {
                Timber.e(TAG, e)
                Result.failure()
            }
}