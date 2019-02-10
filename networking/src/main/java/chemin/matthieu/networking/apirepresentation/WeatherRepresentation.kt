package chemin.matthieu.networking.apirepresentation

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class WeatherRepresentation(
        @Json(name = "main") val main: String,
        @Json(name = "description") val description: String
)