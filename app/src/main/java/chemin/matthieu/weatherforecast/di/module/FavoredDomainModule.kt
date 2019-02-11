package chemin.matthieu.weatherforecast.di.module

import chemin.matthieu.domain.FavoredLocation
import chemin.matthieu.domain.ReadFavoredLocation
import chemin.matthieu.domain.SearchLocation
import chemin.matthieu.domain.UnFavoredLocation
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
    fun providesUnFavoredLocation(
            locationRepository: UnFavoredLocation.LocationRepository
    ) = UnFavoredLocation(locationRepository)
}