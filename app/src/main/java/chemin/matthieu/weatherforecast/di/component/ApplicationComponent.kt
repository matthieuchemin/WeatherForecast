package chemin.matthieu.weatherforecast.di.component

import android.content.Context
import chemin.matthieu.weatherforecast.WeatherApplication
import chemin.matthieu.weatherforecast.di.module.*
import chemin.matthieu.weatherforecast.di.subcomponent.ActivitiesSubComponents
import chemin.matthieu.weatherforecast.di.subcomponent.WorkerSubComponent
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
            FavoredDataStoreModule::class,
            FavoredDomainModule::class,
            FavoredPresentationModule::class,
            ForecastDataStoreModule::class,
            ForecastDomainModule::class,
            ForecastPresentationModule::class,
            ForecastRepositoriesModule::class,
            LocationDataStoreModule::class,
            NetworkingModule::class,
            PresentationModule::class,
            RepositoriesModule::class,
            SchedulingModule::class,
            SyncDomainModule::class,
            TimberModule::class,
            ViewModelBindingModule::class,
            WorkerFactoryModule::class,

            ActivitiesSubComponents::class
        ]
)
interface ApplicationComponent : AndroidInjector<WeatherApplication> {

    fun workersSubComponentBuilde(): WorkerSubComponent.Builder

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): ApplicationComponent
    }
}