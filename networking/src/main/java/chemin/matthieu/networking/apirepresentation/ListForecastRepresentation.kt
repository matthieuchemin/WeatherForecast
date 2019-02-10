package chemin.matthieu.networking.apirepresentation

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ListForecastRepresentation(
    @Json(name = "city") val city: CityRepresentation,
    @Json(name = "list") val list: Array<ForecastRepresentation>
)