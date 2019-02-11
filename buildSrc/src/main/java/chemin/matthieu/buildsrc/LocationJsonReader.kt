package chemin.matthieu.buildsrc

import com.google.gson.stream.JsonReader
import java.io.OutputStreamWriter

private const val ID = "id"
private const val NAME = "name"
private const val COUNTRY = "country"
private const val FRANCE = "FR"

private const val TAG = "LocationJsonReader"

class LocationJsonReader(private val outputStreamWriter: OutputStreamWriter) {

    fun readLocationArray(jsonReader: JsonReader) {
        jsonReader.beginArray()
        outputStreamWriter.write("[\n")
        while (jsonReader.hasNext()) {
            readLocation(jsonReader)
        }
        jsonReader.endArray()
        outputStreamWriter.write("]")
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
                }
            }
        }
        if (id != null && country == FRANCE) {
            outputStreamWriter.write("\t{\n")
            outputStreamWriter.write("\t\t\"$ID\": $id,\n")
            outputStreamWriter.write("\t\t\"$NAME\": \"$cityName\",\n")
            outputStreamWriter.write("\t\t\"$COUNTRY\": \"$country\"\n")
            outputStreamWriter.write("\t},\n")
        }
        jsonReader.endObject()
    }

}