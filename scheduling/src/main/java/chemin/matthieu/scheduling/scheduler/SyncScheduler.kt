package chemin.matthieu.scheduling.scheduler

import androidx.work.*
import chemin.matthieu.scheduling.worker.SyncWorker
import java.util.concurrent.TimeUnit

private const val SYNC_TASK_TAG = "sync_task"

class SyncScheduler {

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

        WorkManager.getInstance().enqueueUniquePeriodicWork("toto", ExistingPeriodicWorkPolicy.REPLACE, syncWork)
    }

}