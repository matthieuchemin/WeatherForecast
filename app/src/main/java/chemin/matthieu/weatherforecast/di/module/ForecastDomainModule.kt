package chemin.matthieu.weatherforecast.di.module

import chemin.matthieu.domain.ReadForecastForPosition
import chemin.matthieu.domain.ReadLocationName
import dagger.Module
import dagger.Provides

@Module
class ForecastDomainModule {

    @Provides
    fun provideReadForecastForPosition(
            forecastRepository: ReadForecastForPosition.ForecastRepository
    ) : ReadForecastForPosition = ReadForecastForPosition(forecastRepository)

    @Provides
    fun providesReadLocationName(
            locationRepository: ReadLocationName.LocationRepository
    ) = ReadLocationName(locationRepository)
}