package chemin.matthieu.weatherforecast

import androidx.work.Configuration
import androidx.work.WorkManager
import chemin.matthieu.weatherforecast.tools.WeatherWorkerFactory
import chemin.matthieu.scheduling.scheduler.SyncScheduler
import chemin.matthieu.weatherforecast.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import timber.log.Tree
import javax.inject.Inject

/**
 * Created by matthieuchemin on 10/02/2019.
 */
class WeatherApplication : DaggerApplication() {

    @Inject
    lateinit var trees : @JvmSuppressWildcards Set<Tree>

    @Inject
    lateinit var syncScheduler: SyncScheduler

    @Inject
    lateinit var weatherWorkerFactory: WeatherWorkerFactory

    override fun onCreate() {
        super.onCreate()
        trees.forEach { Timber.plant(it) }
        WorkManager.initialize(
                this,
                Configuration.Builder().let {
                    it.setWorkerFactory(weatherWorkerFactory)
                    it.build()
                }
        )
        syncScheduler.scheduleSync()
        syncScheduler.scheduleNotifications()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerApplicationComponent.builder()
                    .context(applicationContext)
                    .build()
}