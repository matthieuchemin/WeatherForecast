package chemin.matthieu.weatherforecast.di.module

import chemin.matthieu.repositories.ForecastRepository
import chemin.matthieu.repositories.LocationRepository
import chemin.matthieu.weatherforecast.tools.TimestampBuilder
import dagger.Module
import dagger.Provides

@Module
class RepositoriesModule {

    @Provides
    fun providesCurrentTimestampBuilder(): ForecastRepository.CurrentTimeStampBuilder = TimestampBuilder()

    @Provides
    fun provideForecastRepository(
            remoteForecastDataStore: ForecastRepository.RemoteForecastDataStore,
            localForecastDataStore: ForecastRepository.LocalForecastDataStore,
            currentTimeStampBuilder: ForecastRepository.CurrentTimeStampBuilder
    ) = ForecastRepository(remoteForecastDataStore, localForecastDataStore, currentTimeStampBuilder)

    @Provides
    fun provideLocationRepository(
            locationDataStore: LocationRepository.LocalFavoredLocationDataStore
    ) = LocationRepository(locationDataStore)
}