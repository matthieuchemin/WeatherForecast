package chemin.matthieu.networking.retrofit

import chemin.matthieu.networking.apirepresentation.ListForecastRepresentation
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface OpenWheatherMapRetrofitService {

    @GET("forecast")
    @FormUrlEncoded
    fun getForecast(@Field("id") locationId: Long): Call<ListForecastRepresentation>

}