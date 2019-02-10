package chemin.matthieu.networking.apirepresentation

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ForecastRepresentation(
        @Json(name = "dt") val timestamp: Long,
        @Json(name = "main") val mainForecastRepresentation: MainForecastRepresentation,
        @Json(name = "weather") val weather: Array<WeatherRepresentation>
)