package chemin.matthieu.networking.apirepresentation

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ForecastRepresentation(
        @Json(name = "dt") timestamp: Long,
        @Json(name = "main") mainForecastRepresentation: MainForecastRepresentation,
        @Json(name = "weather") weather: WeatherRepresentation
)