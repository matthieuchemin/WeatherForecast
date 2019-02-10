package chemin.matthieu.weatherforecast.di.subcomponent

import chemin.matthieu.presentation.activity.ForecastActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivitiesSubComponents {

    @ContributesAndroidInjector
    fun contributesForecastActivity(): ForecastActivity
}