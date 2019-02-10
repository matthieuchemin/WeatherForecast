package chemin.matthieu.weatherforecast.di.module

import chemin.matthieu.database.datastore.LocalForecastDataStore
import chemin.matthieu.database.datastore.LocalLocationDataStore
import chemin.matthieu.networking.datastore.RemoteForecastDataStore
import chemin.matthieu.repositories.ForecastRepository
import dagger.Binds
import dagger.Module

@Module
interface ForecastDataStoreModule {

    @Binds
    fun bindsLocalForecastDataStore(localForecastDataStore: LocalForecastDataStore): ForecastRepository.LocalForecastDataStore

    @Binds
    fun bindsLocalLocationDataStore(localLocationDataStore: LocalLocationDataStore): ForecastRepository.LocalLocationDataStore

    @Binds
    fun bindsRemoteForecastDataStore(remoteForecastDataStore: RemoteForecastDataStore): ForecastRepository.RemoteForecastDataStore
}