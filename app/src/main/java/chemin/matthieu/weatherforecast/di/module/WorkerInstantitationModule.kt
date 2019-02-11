package chemin.matthieu.weatherforecast.di.module

import android.content.Context
import androidx.work.WorkerParameters
import chemin.matthieu.domain.SyncForecastForFavoredLocation
import chemin.matthieu.scheduling.worker.SyncWorker
import dagger.Module
import dagger.Provides

@Module
class WorkerInstantitationModule {

    @Provides
    fun providesSyncWorker(
            context: Context,
            workerParameters: WorkerParameters,
            syncForecastForFavoredLocation: SyncForecastForFavoredLocation
    ) = SyncWorker(context, workerParameters, syncForecastForFavoredLocation)
}