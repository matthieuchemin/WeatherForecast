package chemin.matthieu.weatherforecast.di.module

import chemin.matthieu.database.datastore.LocalLocationDataStore
import chemin.matthieu.repositories.LocationRepository
import dagger.Binds
import dagger.Module

@Module
interface LocationDataStoreModule {

    @Binds
    fun binds(localLocationDataStore: LocalLocationDataStore): LocationRepository.LocalFavoredLocationDataStore
}