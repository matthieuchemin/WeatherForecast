package chemin.matthieu.weatherforecast.di.module

import chemin.matthieu.domain.*
import chemin.matthieu.presentation.viewmodel.FavoredLocationViewModel
import chemin.matthieu.presentation.viewmodel.SearchLocationViewModel
import dagger.Module
import dagger.Provides

@Module
class FavoredPresentationModule {

    @Provides
    fun providesFavoredViewModel(readFavoredLocation: ReadFavoredLocation, unFavoredLocationFromFavored: UnFavoredLocationFromFavored) =
            FavoredLocationViewModel(readFavoredLocation, unFavoredLocationFromFavored)

    @Provides
    fun providesSearchViewModel(searchLocation: SearchLocation, favoredLocation: FavoredLocation, unFavoredLocationFromSearch: UnFavoredLocationFromSearch) =
            SearchLocationViewModel(searchLocation, favoredLocation, unFavoredLocationFromSearch)
}