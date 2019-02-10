package chemin.matthieu.database.dao

import androidx.room.Dao
import androidx.room.Query
import chemin.matthieu.database.entities.LocationDatabaseEntity
import chemin.matthieu.repositories.ForecastRepository

@Dao
abstract class LocationDao {

    @Query("SELECT ${LocationDatabaseEntity.CITY_NAME} FROM ${LocationDatabaseEntity.TABLE_NAME} WHERE ${LocationDatabaseEntity.ID}=:locationId")
    abstract fun getLocationCityNameWithId(locationId: Long): String?

    @Query("SELECT * FROM ${LocationDatabaseEntity.TABLE_NAME} " +
            "WHERE ${LocationDatabaseEntity.FAVORED}=1 " +
            "ORDER BY ${LocationDatabaseEntity.COUNTRY} ASC, ${LocationDatabaseEntity.CITY_NAME} ASC " +
            "LIMIT 250")// we are not using paged list so we keep low the number of element in this list
    abstract fun getFavoredLocation(): List<LocationDatabaseEntity>
}