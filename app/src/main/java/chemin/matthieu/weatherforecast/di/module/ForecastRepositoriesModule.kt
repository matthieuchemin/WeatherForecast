package chemin.matthieu.weatherforecast.di.module

import chemin.matthieu.domain.ReadForecastForPosition
import chemin.matthieu.repositories.ForecastRepository
import dagger.Binds
import dagger.Module

@Module
interface ForecastRepositoriesModule {

    @Binds
    fun binds(forecastRepository: ForecastRepository): ReadForecastForPosition.ForecastRepository
}