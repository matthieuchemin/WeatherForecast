package chemin.matthieu.weatherforecast.di.subcomponent

import chemin.matthieu.presentation.activity.FavoredLocationsActivity
import chemin.matthieu.presentation.activity.ForecastActivity
import chemin.matthieu.presentation.activity.SearchLocationsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivitiesSubComponents {

    @ContributesAndroidInjector
    fun contributesForecastActivity(): ForecastActivity

    @ContributesAndroidInjector
    fun contributesFavoredLocationsActivity(): FavoredLocationsActivity

    @ContributesAndroidInjector
    fun contributesSearchLocationsActivity(): SearchLocationsActivity
}