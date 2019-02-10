package chemin.matthieu.database.dao

import androidx.room.*
import chemin.matthieu.database.entities.ForecastDatabaseEntity

@Dao
abstract class ForecastDao {

    @Query("SELECT * FROM ${ForecastDatabaseEntity.TABLE_NAME} WHERE ${ForecastDatabaseEntity.ID_LOCATION}=:locationId ORDER BY ${ForecastDatabaseEntity.TIMESTAMP} ASC LIMIT 1")
    abstract fun getForecastFromDb(locationId: Long): ForecastDatabaseEntity?

    @Query("DELETE FROM ${ForecastDatabaseEntity.TABLE_NAME} WHERE ${ForecastDatabaseEntity.ID_LOCATION}=:locationId")
    protected abstract fun clearForecastFromDb(locationId: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    protected abstract fun writeForecastToDb(forecastArray: List<ForecastDatabaseEntity>)

    @Transaction
    fun clearAndWriteForecast(locationId: Long, forecastArray: List<ForecastDatabaseEntity>) {
        clearForecastFromDb(locationId)
        writeForecastToDb(forecastArray)
    }
}