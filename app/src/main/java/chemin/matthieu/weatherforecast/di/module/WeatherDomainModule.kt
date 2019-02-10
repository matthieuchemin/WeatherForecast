package chemin.matthieu.weatherforecast.di.module

import chemin.matthieu.domain.ReadForecastForPosition
import dagger.Module
import dagger.Provides

@Module
class WeatherDomainModule {

    @Provides
    fun provideReadForecastForPosition(
            forecastRepository: ReadForecastForPosition.ForecastRepository,
            taskScheduler: ReadForecastForPosition.TaskScheduler
    ) : ReadForecastForPosition = ReadForecastForPosition(forecastRepository, taskScheduler)
}