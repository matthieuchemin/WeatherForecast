package chemin.matthieu.weatherforecast.di.module

import chemin.matthieu.domain.ReadForecastForAllFavoredLocation
import chemin.matthieu.domain.ReadForecastForPosition
import chemin.matthieu.domain.ReadLocationName
import chemin.matthieu.repositories.ForecastRepository
import chemin.matthieu.repositories.LocationRepository
import dagger.Binds
import dagger.Module

@Module
interface ForecastRepositoriesModule {

    @Binds
    fun bindsReadForecastForPositionForecastRepository(forecastRepository: ForecastRepository): ReadForecastForPosition.ForecastRepository

    @Binds
    fun bindsReadLocationNameLocationRepository(locationRepository: LocationRepository): ReadLocationName.LocationRepository

    @Binds
    fun bindsReadForeactForAllFavoredLocationForecastRepository(forecastRepository: ForecastRepository): ReadForecastForAllFavoredLocation.ForecastRepository

    @Binds
    fun bindsReadForecastForAllFavoredLocationLocationRepository(locationRepository: LocationRepository): ReadForecastForAllFavoredLocation.LocationRepository
}