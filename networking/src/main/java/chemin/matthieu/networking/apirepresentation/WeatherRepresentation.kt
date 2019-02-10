package chemin.matthieu.networking.apirepresentation

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class WeatherRepresentation(
        @Json(name = "main") main: String,
        @Json(name = "description") description: String
)