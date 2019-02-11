package chemin.matthieu.weatherforecast.di.module

import chemin.matthieu.domain.NextNotificationTime
import chemin.matthieu.domain.ReadForecastForPosition
import chemin.matthieu.scheduling.scheduler.SyncScheduler
import dagger.Module
import dagger.Provides

@Module
class SchedulingModule {

    @Provides
    fun providesTaskScheduler() : ReadForecastForPosition.TaskScheduler {
        return object : ReadForecastForPosition.TaskScheduler {
            override fun scheduleNextExecution(delayInMillisecond: Long) {
                // TODO implement a real task scheduler
            }

        }
    }

    @Provides
    fun providesSyncScheduler(nextNotificationTime: NextNotificationTime) = SyncScheduler(nextNotificationTime)
}