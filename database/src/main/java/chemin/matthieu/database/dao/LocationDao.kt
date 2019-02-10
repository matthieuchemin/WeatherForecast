package chemin.matthieu.database.dao

import androidx.room.Dao
import androidx.room.Query
import chemin.matthieu.database.entities.LocationDatabaseEntity
import chemin.matthieu.repositories.ForecastRepository

@Dao
abstract class LocationDao : ForecastRepository.LocalLocationDataStore {

    @Query("SELECT ${LocationDatabaseEntity.CITY_NAME} FROM ${LocationDatabaseEntity.TABLE_NAME} WHERE ${LocationDatabaseEntity.ID}=:id")
    abstract override fun getLocationCityNameWithId(locationId: Long): String?
}