package chemin.matthieu.database.location

import android.content.res.AssetManager
import android.util.JsonReader
import java.io.InputStreamReader

private const val LOCATIONS_FILE_NAME = "locations.json"

class LocationWriter(private val locationJsonReader: LocationJsonReader, private val assetManager: AssetManager) {

    fun writeLocationToDatabase() {
        assetManager.open(LOCATIONS_FILE_NAME).use { inputStream ->
            InputStreamReader(inputStream).use { inputStreamReader ->
                JsonReader(inputStreamReader).use { jsonReader ->
                    jsonReader.isLenient = true // TODO this is because our json is badly formatted (and this is not even working properly)
                    locationJsonReader.readLocationArray(jsonReader)
                    jsonReader.close()
                }
                inputStreamReader.close()
            }
            inputStream.close()
        }
    }
}