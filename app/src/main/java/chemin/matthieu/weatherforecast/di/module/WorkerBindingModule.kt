package chemin.matthieu.weatherforecast.di.module

import androidx.work.Worker
import chemin.matthieu.scheduling.worker.SyncWorker
import chemin.matthieu.weatherforecast.di.annotation.WorkerKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerBindingModule {

    @Binds
    @IntoMap
    @WorkerKey(SyncWorker::class)
    fun bindSyncWorker(worker: SyncWorker): Worker
}