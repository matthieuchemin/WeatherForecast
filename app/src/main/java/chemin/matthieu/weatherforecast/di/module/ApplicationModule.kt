package chemin.matthieu.weatherforecast.di.module

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import chemin.matthieu.scheduling.notifications.NotificationBuilder
import chemin.matthieu.scheduling.worker.NotificationWorker
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideNotifcationManager(context: Context) = NotificationManagerCompat.from(context)

    @Provides
    fun provideNotifcationBuilder(context: Context): NotificationWorker.NotificationBuilder {
        return NotificationBuilder(context, "some_channel_id")// TODO create a default channel for Oreo and above
    }
}