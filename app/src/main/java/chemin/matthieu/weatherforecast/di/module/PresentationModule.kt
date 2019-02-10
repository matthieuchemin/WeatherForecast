package chemin.matthieu.weatherforecast.di.module

import android.content.Context
import android.view.LayoutInflater
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import chemin.matthieu.presentation.adapter.LocationAdapter
import chemin.matthieu.presentation.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class PresentationModule {

    @Provides
    fun provideViewModelFactory(
            creators: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
    ): ViewModelProvider.Factory = ViewModelFactory(creators)

    @Provides
    fun providesLocationAdapter(layoutInflater: LayoutInflater) = LocationAdapter(layoutInflater)

    @Provides
    fun providesLayoutInflater(context: Context) = LayoutInflater.from(context)

}