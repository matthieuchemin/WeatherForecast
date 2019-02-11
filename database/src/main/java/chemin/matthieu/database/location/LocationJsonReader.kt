package chemin.matthieu.database.location

import android.util.JsonReader
import chemin.matthieu.commontools.d
import chemin.matthieu.commontools.exhaustive
import chemin.matthieu.commontools.v
import chemin.matthieu.database.dao.LocationDao
import chemin.matthieu.database.entities.LocationDatabaseEntity
import timber.log.Timber

private const val ID = "id"
private const val NAME = "name"
private const val COUNTRY = "country"

private const val TAG = "LocationJsonReader"

class LocationJsonReader(private val locationDao: LocationDao) {

    fun readLocationArray(jsonReader: JsonReader) {
        jsonReader.beginArray()
        while (jsonReader.hasNext()) {
            readLocation(jsonReader)
        }
        jsonReader.endArray()
    }

    private fun readLocation(jsonReader: JsonReader) {
        jsonReader.beginObject()
        var id: Long? = null
        var cityName: String? = null
        var country: String? = null
        while (jsonReader.hasNext()) {
            val name = jsonReader.nextName()
            when (name) {
                ID -> id = jsonReader.nextLong()
                NAME -> cityName = jsonReader.nextString()
                COUNTRY -> country = jsonReader.nextString()
                else -> {
                    jsonReader.skipValue()
                    Timber.d(TAG, "skipping value for json key $name")
                }
            }.exhaustive
        }
        if (id != null) {
            val databaseEntity = LocationDatabaseEntity(
                    id = id, cityName = cityName, country = country, favored = false
            )
            locationDao.insert(databaseEntity)
            Timber.v(TAG, "location $databaseEntity inserted")
        }
        jsonReader.endObject()
    }

}