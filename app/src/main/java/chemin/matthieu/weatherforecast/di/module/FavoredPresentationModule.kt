package chemin.matthieu.weatherforecast.di.module

import chemin.matthieu.domain.ReadFavoredLocation
import chemin.matthieu.domain.SearchLocation
import chemin.matthieu.presentation.viewmodel.FavoredLocationViewModel
import chemin.matthieu.presentation.viewmodel.SearchLocationViewModel
import dagger.Module
import dagger.Provides

@Module
class FavoredPresentationModule {

    @Provides
    fun providesFavoredViewModel(readFavoredLocation: ReadFavoredLocation) = FavoredLocationViewModel(readFavoredLocation)

    @Provides
    fun providesSearchViewModel(searchLocation: SearchLocation) = SearchLocationViewModel(searchLocation)
}