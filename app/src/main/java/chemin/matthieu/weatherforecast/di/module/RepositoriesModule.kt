package chemin.matthieu.weatherforecast.di.module

import chemin.matthieu.repositories.ForecastRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoriesModule {

    @Provides
    fun provideForecastRepository(
            localLocationDataStore: ForecastRepository.LocalLocationDataStore,
            remoteForecastDataStore: ForecastRepository.RemoteForecastDataStore,
            localForecastDataStore: ForecastRepository.LocalForecastDataStore
    ) = ForecastRepository(localLocationDataStore, remoteForecastDataStore, localForecastDataStore)
}