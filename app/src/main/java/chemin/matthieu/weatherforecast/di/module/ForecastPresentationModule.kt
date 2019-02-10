package chemin.matthieu.weatherforecast.di.module

import chemin.matthieu.domain.ReadForecastForPosition
import chemin.matthieu.presentation.model.DisplayForecastMapper
import chemin.matthieu.presentation.viewmodel.ForecastViewModel
import dagger.Module
import dagger.Provides
import java.text.DateFormat
import java.text.SimpleDateFormat

@Module
class ForecastPresentationModule {

    @Provides
    fun provideDateFormat(): DateFormat = DateFormat.getDateTimeInstance()

    @Provides
    fun provideDisplayForecastMapper(dateFormat: DateFormat) = DisplayForecastMapper(dateFormat)

    @Provides
    fun provideForecastViewModel(
            readForecastForPosition: ReadForecastForPosition,
            displayForecastMapper: DisplayForecastMapper
    ) = ForecastViewModel(
            readForecastForPosition,
            displayForecastMapper
    )
}