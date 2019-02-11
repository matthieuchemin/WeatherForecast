package chemin.matthieu.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import chemin.matthieu.database.entities.LocationDatabaseEntity
import chemin.matthieu.repositories.ForecastRepository

@Dao
abstract class LocationDao {

    @Query("SELECT ${LocationDatabaseEntity.CITY_NAME} FROM ${LocationDatabaseEntity.TABLE_NAME} WHERE ${LocationDatabaseEntity.ID}=:locationId")
    abstract fun getLocationCityNameWithId(locationId: Long): String?

    @Query("SELECT * FROM ${LocationDatabaseEntity.TABLE_NAME} " +
            "WHERE ${LocationDatabaseEntity.FAVORED}=1 " +
            "ORDER BY ${LocationDatabaseEntity.CITY_NAME} ASC " +
            "LIMIT 250")// we are not using paged list so we keep low the number of element in this list
    abstract fun getFavoredLocation(): List<LocationDatabaseEntity>

    @Query("SELECT * FROM ${LocationDatabaseEntity.TABLE_NAME} " +
            "WHERE ${LocationDatabaseEntity.CITY_NAME} LIKE :search " +
            "ORDER BY ${LocationDatabaseEntity.CITY_NAME} ASC " +
            "LIMIT 250")// we are not using paged list so we keep low the number of element in this list
    abstract fun searchLocation(search: String): List<LocationDatabaseEntity>

    @Query("SELECT count(*) FROM ${LocationDatabaseEntity.TABLE_NAME}")
    abstract fun countLocation(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(location: LocationDatabaseEntity)
}