package chemin.matthieu.networking.retrofit

import chemin.matthieu.networking.apirepresentation.ListForecastRepresentation
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWheatherMapRetrofitService {

    @GET("forecast")
    fun getForecast(
            @Query("id") locationId: Long,
            @Query("appid") apiKey: String
    ): Call<ListForecastRepresentation>

}