package chemin.matthieu.database.dao

import androidx.room.Dao
import androidx.room.Query
import chemin.matthieu.database.entities.LocationDatabaseEntity

@Dao
interface LocationDao {

    @Query("SELECT ${LocationDatabaseEntity.CITY_NAME} FROM ${LocationDatabaseEntity.TABLE_NAME} WHERE ${LocationDatabaseEntity.ID}=:id")
    fun getLocationCityName(id: Long): String?
}