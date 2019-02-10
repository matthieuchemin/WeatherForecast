package chemin.matthieu.weatherforecast.di.module

import chemin.matthieu.domain.ReadFavoredLocation
import chemin.matthieu.presentation.viewmodel.FavoredLocationViewModel
import dagger.Module
import dagger.Provides

@Module
class FavoredPresentationModule {

    @Provides
    fun providesFavoredViewModel(readFavoredLocation: ReadFavoredLocation) = FavoredLocationViewModel(readFavoredLocation)
}