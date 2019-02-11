package chemin.matthieu.weatherforecast.di.module

import chemin.matthieu.weatherforecast.di.subcomponent.WorkerSubComponent
import chemin.matthieu.weatherforecast.tools.WeatherWorkerFactory
import dagger.Module
import dagger.Provides

@Module
class WorkerFactoryModule {

    @Provides
    fun providesWorkerFactory(workerSubComponentBuilder: WorkerSubComponent.Builder) = WeatherWorkerFactory(workerSubComponentBuilder)
}