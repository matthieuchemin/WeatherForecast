package chemin.matthieu.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
        tableName = ForecastDatabaseEntity.TABLE_NAME,
        primaryKeys = [
            ForecastDatabaseEntity.ID_LOCATION,
            ForecastDatabaseEntity.TIMESTAMP
        ]
)
data class ForecastDatabaseEntity(
        @ColumnInfo(name = ID_LOCATION) var idLocation: Long,
        @ColumnInfo(name = TIMESTAMP) var timestamp: Long,
        @ColumnInfo(name = WHEATER) var weather: String,
        @ColumnInfo(name = DESCRIPTION) var description: String,
        @ColumnInfo(name = TEMPERATURE) var temperature: Int,
        @ColumnInfo(name = TEMPERATURE_MIN) var temperatureMin: Int,
        @ColumnInfo(name = TEMPERATURE_MAX) var temperatureMax: Int
) {
    internal companion object {
        const val TABLE_NAME = "forecast"
        const val ID_LOCATION = "id_location"
        const val TIMESTAMP = "timestamp"
        const val WHEATER = "weather"
        const val DESCRIPTION = "description"
        const val TEMPERATURE = "temperature"
        const val TEMPERATURE_MIN = "temperature_min"
        const val TEMPERATURE_MAX = "temperature_max"
    }
}