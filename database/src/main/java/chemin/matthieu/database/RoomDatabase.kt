package chemin.matthieu.database

import androidx.room.Database
import chemin.matthieu.database.entities.ForecastDatabaseEntity
import chemin.matthieu.database.entities.LocationDatabaseEntity

private const val DATABASE_VERSION = 1

@Database(
        entities = [
            ForecastDatabaseEntity::class,
            LocationDatabaseEntity::class
        ],
        version = DATABASE_VERSION
)
class RoomDatabase {

}