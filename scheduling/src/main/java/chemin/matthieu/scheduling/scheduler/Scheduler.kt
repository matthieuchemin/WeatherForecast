package chemin.matthieu.scheduling.scheduler

import androidx.work.*
import chemin.matthieu.domain.NextNotificationTime
import chemin.matthieu.scheduling.worker.NotificationWorker
import chemin.matthieu.scheduling.worker.SyncWorker
import java.util.concurrent.TimeUnit

private const val SYNC_TASK_TAG = "sync_task"
private const val WORK_NAME = "sync_work"

class Scheduler(
        private val nextNotificationTime: NextNotificationTime,
        private val workManager: WorkManager
) {

    fun scheduleSync() {
        val syncConstraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

        val targetPeriod = TimeUnit.SECONDS.toMillis(6)
        val period = Math.max(targetPeriod, PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS)

        val syncBuilder = PeriodicWorkRequest.Builder(SyncWorker::class.java, period, TimeUnit.MILLISECONDS)
                .addTag(SYNC_TASK_TAG)
                .setConstraints(syncConstraints)

        val syncWork = syncBuilder.build()

        workManager.enqueueUniquePeriodicWork(WORK_NAME, ExistingPeriodicWorkPolicy.REPLACE, syncWork)
    }

    fun scheduleNotifications() {
        val time = System.currentTimeMillis()
        val nextTime = nextNotificationTime.perform(time)
        val delay = nextTime - time

        val notificationBuilder = OneTimeWorkRequest.Builder(NotificationWorker::class.java)
                .setInitialDelay(delay, TimeUnit.MILLISECONDS)

        workManager.enqueue(notificationBuilder.build())
    }

}