package chemin.matthieu.weatherforecast.di.module

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import androidx.work.WorkerParameters
import chemin.matthieu.domain.ReadForecastForAllFavoredLocation
import chemin.matthieu.domain.SyncForecastForFavoredLocation
import chemin.matthieu.scheduling.scheduler.SyncScheduler
import chemin.matthieu.scheduling.worker.NotificationWorker
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

    @Provides
    fun providesNotificationWorker(
            context: Context,
            workerParameters: WorkerParameters,
            syncScheduler: SyncScheduler,
            readForecastForAllFavoredLocation: ReadForecastForAllFavoredLocation,
            notificationManager: NotificationManagerCompat,
            notificationBuilder: NotificationWorker.NotificationBuilder
    ) = NotificationWorker(
            context,
            workerParameters,
            syncScheduler,
            readForecastForAllFavoredLocation,
            notificationManager,
            notificationBuilder
    )
}