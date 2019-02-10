package chemin.matthieu.presentation.model

import chemin.matthieu.entities.Forecast
import java.text.DateFormat
import java.util.*

class DisplayForecast(
    val readableDate: String,
    val weather: String,
    val detail: String,
    val temperature: String,
    val temperatureMin: String,
    val temperatureMax: String
)

class DisplayForecastMapper(private val dateFormat: DateFormat) {

    fun map(forecast: Forecast): DisplayForecast {
        val readableDate = dateFormat.format(Date(forecast.timeStamp))
        val weather = forecast.weather
        val detail = forecast.weatherDescription
        val temperature = forecast.temperature.toString()
        val temperatureMin = forecast.temperatureMin.toString()
        val temperatureMax = forecast.temperatureMax.toString()
        return DisplayForecast(
                readableDate = readableDate,
                weather = weather,
                detail = detail,
                temperature = temperature,
                temperatureMin = temperatureMin,
                temperatureMax = temperatureMax
        )
    }
}