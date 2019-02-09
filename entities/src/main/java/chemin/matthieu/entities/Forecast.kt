package chemin.matthieu.entities

data class Forecast(
        val timeStamp: Long,
        val weather: String,
        val weatherDescription: String,
        val temperature: Int,
        val temperatureMin: Int,
        val temperatureMax: Int
)