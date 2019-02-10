package chemin.matthieu.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = LocationDatabaseEntity.TABLE_NAME)
data class LocationDatabaseEntity(
        @ColumnInfo(name = ID) @PrimaryKey var id: Long,
        @ColumnInfo(name = CITY_NAME) var cityName: String?
) {
    internal companion object {
        const val TABLE_NAME = "location"
        const val ID = "id"
        const val CITY_NAME = "city_name"
    }
}