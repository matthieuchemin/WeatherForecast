package chemin.matthieu.weatherforecast.di.module

import chemin.matthieu.domain.ReadFavoredLocation
import chemin.matthieu.domain.ReadForecastForPosition
import chemin.matthieu.domain.SyncForecastForFavoredLocation
import dagger.Module
import dagger.Provides

@Module
class SyncDomainModule {

    @Provides
    fun providesSyncForecastForFavoredLocation(readFavoredLocation: ReadFavoredLocation, readForecastForPosition: ReadForecastForPosition) =
            SyncForecastForFavoredLocation(readFavoredLocation, readForecastForPosition)
}