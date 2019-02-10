package chemin.matthieu.database

import androidx.room.Database
import androidx.room.RoomDatabase
import chemin.matthieu.database.dao.ForecastDao
import chemin.matthieu.database.dao.LocationDao
import chemin.matthieu.database.entities.ForecastDatabaseEntity
import chemin.matthieu.database.entities.LocationDatabaseEntity

private const val DATABASE_VERSION = 3
const val DATABASE_NAME = "WeatherForecast"

@Database(
        entities = [
            ForecastDatabaseEntity::class,
            LocationDatabaseEntity::class
        ],
        version = DATABASE_VERSION
)
abstract class WheatherForecastDatabase : RoomDatabase() {

    abstract fun locationDao(): LocationDao

    abstract fun forecastDao(): ForecastDao
}