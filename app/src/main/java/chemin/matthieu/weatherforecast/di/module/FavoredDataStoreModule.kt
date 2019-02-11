package chemin.matthieu.weatherforecast.di.module

import chemin.matthieu.domain.*
import chemin.matthieu.repositories.LocationRepository
import dagger.Binds
import dagger.Module

@Module
interface FavoredDataStoreModule {

    @Binds
    fun bindsLocalForecastDataStore(locationRepository: LocationRepository): ReadFavoredLocation.FavoredLocationRepository

    @Binds
    fun bindsSearchLocationRepository(locationRepository: LocationRepository): SearchLocation.LocationRepository

    @Binds
    fun bindsFavoredLocationRepository(locationRepository: LocationRepository): FavoredLocation.LocationRepository

    @Binds
    fun bindsUnFavoredLocationFromSeachRepository(locationRepository: LocationRepository): UnFavoredLocationFromSearch.LocationRepository

    @Binds
    fun bindUnFavoredLocationFromFavoredRepository(locationRepository: LocationRepository): UnFavoredLocationFromFavored.LocationRepository
}