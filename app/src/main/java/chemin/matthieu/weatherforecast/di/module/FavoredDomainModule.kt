package chemin.matthieu.weatherforecast.di.module

import chemin.matthieu.domain.ReadFavoredLocation
import dagger.Module
import dagger.Provides

@Module
class FavoredDomainModule {

    @Provides
    fun providesReadFavoredLocations(
            favoredLocationRepository: ReadFavoredLocation.FavoredLocationRepository
    ) = ReadFavoredLocation(favoredLocationRepository)
}