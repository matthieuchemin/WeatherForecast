package chemin.matthieu.weatherforecast.di.module

import chemin.matthieu.domain.NextNotificationTime
import chemin.matthieu.domain.ReadForecastForAllFavoredLocation
import dagger.Module
import dagger.Provides

@Module
class NotificationDomainModule {

    @Provides
    fun providesNextNotificationTime() = NextNotificationTime()

    @Provides
    fun providesReadForecastForAllFavoredLcation(
        locationRepository: ReadForecastForAllFavoredLocation.LocationRepository,
        forecastRepository: ReadForecastForAllFavoredLocation.ForecastRepository
    ) = ReadForecastForAllFavoredLocation(locationRepository, forecastRepository)
}