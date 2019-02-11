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

        val syncBuilder = PeriodicWorkRequest.Builder(SyncWorker::class.java, 6, TimeUnit.SECONDS)
                .addTag(SYNC_TASK_TAG)
//                .setConstraints(syncConstraints)

        val syncWork = syncBuilder.build()

        WorkManager.getInstance().enqueueUniquePeriodicWork("toto", ExistingPeriodicWorkPolicy.REPLACE, syncWork)
    }

}