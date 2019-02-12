package chemin.matthieu.weatherforecast.di.module

import android.content.Context
import androidx.work.Configuration
import androidx.work.WorkManager
import chemin.matthieu.domain.NextNotificationTime
import chemin.matthieu.domain.ReadForecastForPosition
import chemin.matthieu.scheduling.scheduler.Scheduler
import chemin.matthieu.weatherforecast.tools.WeatherWorkerFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SchedulingModule {

    @Provides
    fun providesTaskScheduler(): ReadForecastForPosition.TaskScheduler {
        return object : ReadForecastForPosition.TaskScheduler {
            override fun scheduleNextExecution(delayInMillisecond: Long) {
                // TODO implement a real task scheduler
            }

        }
    }

    @Singleton
    @Provides
    fun providesWorkManager(context: Context, weatherWorkerFactory: WeatherWorkerFactory): WorkManager {
        WorkManager.initialize(
                context,
                Configuration.Builder().let {
                    it.setWorkerFactory(weatherWorkerFactory)
                    it.build()
                }
        )
        return WorkManager.getInstance()
    }

    @Provides
    fun providesSyncScheduler(nextNotificationTime: NextNotificationTime, workManager: WorkManager) =
            Scheduler(nextNotificationTime, workManager)
}
