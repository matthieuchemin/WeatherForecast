package chemin.matthieu.networking.apirepresentation

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class MainForecastRepresentation(
        @Json(name = "temp") val temperature: Double,
        @Json(name = "temp_min") val temperatureMin: Double,
        @Json(name = "temp_max") val temperatureMax: Double
)