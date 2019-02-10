package chemin.matthieu.weatherforecast

import chemin.matthieu.weatherforecast.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Created by matthieuchemin on 10/02/2019.
 */
class WeatherApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
            DaggerApplicationComponent.builder()
                    .context(applicationContext)
                    .build()
}