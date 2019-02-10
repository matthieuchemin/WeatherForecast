package chemin.matthieu.weatherforecast.di.component

import android.content.Context
import chemin.matthieu.weatherforecast.WeatherApplication
import chemin.matthieu.weatherforecast.di.module.*
import chemin.matthieu.weatherforecast.di.subcomponent.ActivitiesSubComponents
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,

            DatabaseModule::class,
            ForecastDataStoreModule::class,
            ForecastPresentationModule::class,
            ForecastRepositoriesModule::class,
            NetworkingModule::class,
            PresentationModule::class,
            RepositoriesModule::class,
            SchedulingModule::class,
            TimberModule::class,
            ViewModelBindingModule::class,
            WeatherDomainModule::class,

            ActivitiesSubComponents::class
        ]
)
interface ApplicationComponent : AndroidInjector<WeatherApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): ApplicationComponent
    }
}