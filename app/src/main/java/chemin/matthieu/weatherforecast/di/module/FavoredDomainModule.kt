package chemin.matthieu.weatherforecast.di.module

import chemin.matthieu.domain.*
import dagger.Module
import dagger.Provides

@Module
class FavoredDomainModule {

    @Provides
    fun providesReadFavoredLocations(
            favoredLocationRepository: ReadFavoredLocation.FavoredLocationRepository
    ) = ReadFavoredLocation(favoredLocationRepository)

    @Provides
    fun providesSearchLocations(
            locationRepository: SearchLocation.LocationRepository
    ) = SearchLocation(locationRepository)

    @Provides
    fun providesFavoredLocation(
            locationRepository: FavoredLocation.LocationRepository
    ) = FavoredLocation(locationRepository)

    @Provides
    fun providesUnFavoredFromSearchLocation(
            locationFromSearchRepository: UnFavoredLocationFromSearch.LocationRepository
    ) = UnFavoredLocationFromSearch(locationFromSearchRepository)

    @Provides
    fun providesUnFavoredFromFavoredLocation(
            locationFromFavoredRepository: UnFavoredLocationFromFavored.LocationRepository
    ) = UnFavoredLocationFromFavored(locationFromFavoredRepository)
}