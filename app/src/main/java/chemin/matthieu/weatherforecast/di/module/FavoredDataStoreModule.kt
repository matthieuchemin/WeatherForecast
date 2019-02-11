package chemin.matthieu.weatherforecast.di.module

import chemin.matthieu.domain.ReadFavoredLocation
import chemin.matthieu.domain.SearchLocation
import chemin.matthieu.repositories.LocationRepository
import dagger.Binds
import dagger.Module

@Module
interface FavoredDataStoreModule {

    @Binds
    fun bindsLocalForecastDataStore(locationRepository: LocationRepository): ReadFavoredLocation.FavoredLocationRepository

    @Binds
    fun bindsSearchLocationRepository(locationRepository: LocationRepository): SearchLocation.LocationRepository
}