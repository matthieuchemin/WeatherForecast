package chemin.matthieu.networking.retrofit

import chemin.matthieu.networking.apirepresentation.ListForecastRepresentation
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "2f3c56f42070ae5fd0f3f0634620c213"

interface OpenWeatherMapRetrofitService {

    @GET("forecast?appid=$API_KEY&units=metric")
    fun getForecast( @Query("id") locationId: Long): Call<ListForecastRepresentation>

}