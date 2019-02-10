package chemin.matthieu.weatherforecast.di.module

import chemin.matthieu.database.datastore.LocalForecastDataStore
import chemin.matthieu.domain.ReadFavoredLocation
import chemin.matthieu.networking.datastore.RemoteForecastDataStore
import chemin.matthieu.repositories.ForecastRepository
import chemin.matthieu.repositories.LocationRepository
import dagger.Binds
import dagger.Module

@Module
interface FavoredDataStoreModule {

    @Binds
    fun bindsLocalForecastDataStore(locationRepository: LocationRepository): ReadFavoredLocation.FavoredLocationRepository
}