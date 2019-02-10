package chemin.matthieu.weatherforecast.di.module

import chemin.matthieu.weatherforecast.tools.LogTree
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import timber.log.Tree

@Module
class TimberModule {

    @IntoSet
    @Provides
    fun providesLogTree(): Tree = LogTree()
}