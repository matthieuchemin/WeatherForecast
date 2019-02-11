package chemin.matthieu.weatherforecast.di.module

import androidx.lifecycle.ViewModel
import chemin.matthieu.presentation.viewmodel.FavoredLocationViewModel
import chemin.matthieu.presentation.viewmodel.ForecastViewModel
import chemin.matthieu.presentation.viewmodel.SearchLocationViewModel
import chemin.matthieu.weatherforecast.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelBindingModule {

    @IntoMap
    @Binds
    @ViewModelKey(ForecastViewModel::class)
    fun bindsForecast(forecastViewModel: ForecastViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(FavoredLocationViewModel::class)
    fun bindsFavoredLocation(favoredLocationViewModel: FavoredLocationViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(SearchLocationViewModel::class)
    fun bindsSearchLocation(searchLocationViewModel: SearchLocationViewModel): ViewModel
}