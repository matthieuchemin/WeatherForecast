package chemin.matthieu.weatherforecast.di.module

import android.content.Context
import android.content.res.AssetManager
import androidx.room.Room
import chemin.matthieu.database.DATABASE_NAME
import chemin.matthieu.database.WheatherForecastDatabase
import chemin.matthieu.database.dao.ForecastDao
import chemin.matthieu.database.dao.LocationDao
import chemin.matthieu.database.datastore.LocalForecastDataStore
import chemin.matthieu.database.datastore.LocalLocationDataStore
import chemin.matthieu.database.location.LocationJsonReader
import chemin.matthieu.database.location.LocationWriter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideWeatherDatabase(context: Context): WheatherForecastDatabase =
            Room.databaseBuilder(context, WheatherForecastDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()

    @Provides
    fun provideLocationDao(wheatherForecastDatabase: WheatherForecastDatabase) =
            wheatherForecastDatabase.locationDao()

    @Provides
    fun provideForecastDao(wheatherForecastDatabase: WheatherForecastDatabase) =
            wheatherForecastDatabase.forecastDao()

    @Provides
    fun provideLocalForecastDataStore(forecastDao: ForecastDao) = LocalForecastDataStore(forecastDao)

    @Provides
    fun provideLocalLocationDataStore(locationDao: LocationDao, locationWriter: LocationWriter) =
            LocalLocationDataStore(locationDao, locationWriter)

    @Provides
    fun providesLocationWriter(locationJsonReader: LocationJsonReader, assetManager: AssetManager) =
            LocationWriter(locationJsonReader, assetManager)

    @Provides
    fun provideLocationJsonReader(locationDao: LocationDao) = LocationJsonReader(locationDao)

    @Provides
    fun providesAssetManager(context: Context) = context.assets

}