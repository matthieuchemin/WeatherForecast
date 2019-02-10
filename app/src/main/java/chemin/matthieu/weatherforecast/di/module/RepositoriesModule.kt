package chemin.matthieu.weatherforecast.di.module

import chemin.matthieu.repositories.ForecastRepository
import chemin.matthieu.weatherforecast.tools.TimestampBuilder
import dagger.Module
import dagger.Provides

@Module
class RepositoriesModule {

    @Provides
    fun providesCurrentTimestampBuilder(): ForecastRepository.CurrentTimeStampBuilder = TimestampBuilder()

    @Provides
    fun provideForecastRepository(
            localLocationDataStore: ForecastRepository.LocalLocationDataStore,
            remoteForecastDataStore: ForecastRepository.RemoteForecastDataStore,
            localForecastDataStore: ForecastRepository.LocalForecastDataStore,
            currentTimeStampBuilder: ForecastRepository.CurrentTimeStampBuilder
    ) = ForecastRepository(localLocationDataStore, remoteForecastDataStore, localForecastDataStore, currentTimeStampBuilder)
}