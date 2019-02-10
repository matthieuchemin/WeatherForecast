package chemin.matthieu.networking.apirepresentation

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CityRepresentation(
        @Json(name = "id") val id: Long,
        @Json(name = "name") val name: String
)