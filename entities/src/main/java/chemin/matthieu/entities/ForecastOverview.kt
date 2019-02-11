package chemin.matthieu.entities

data class ForecastOverview(
        val locationId: Long,
        val locationName: String,
        val timeStamp: Long,
        val weather: String,
        val temperature: Int
)