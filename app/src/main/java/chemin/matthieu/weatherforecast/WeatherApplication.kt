package chemin.matthieu.weatherforecast

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

    override fun onCreate() {
        super.onCreate()
        trees.forEach { Timber.plant(it) }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerApplicationComponent.builder()
                    .context(applicationContext)
                    .build()
}