package chemin.matthieu.weatherforecast.di.module

import androidx.lifecycle.ViewModel
import chemin.matthieu.presentation.viewmodel.ForecastViewModel
import chemin.matthieu.weatherforecast.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelBindingModule {

    @IntoMap
    @Binds
    @ViewModelKey(ForecastViewModel::class)
    fun binds(forecastViewModel: ForecastViewModel): ViewModel
}