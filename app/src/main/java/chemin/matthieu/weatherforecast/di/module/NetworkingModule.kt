package chemin.matthieu.weatherforecast.di.module

import chemin.matthieu.networking.datastore.RemoteForecastDataStore
import chemin.matthieu.networking.retrofit.OpenWeatherMapRetrofitService
import chemin.matthieu.weatherforecast.BuildConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class NetworkingModule {

    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder().build()

    @Provides
    fun provideMoshi() = Moshi.Builder().build()

    @Reusable
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
            Retrofit.Builder()
                    .baseUrl(BuildConfig.OPEN_WEATHER_BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build()

    @Reusable
    @Provides
    fun provideOpenWeatherRetrofitService(retrofit: Retrofit): OpenWeatherMapRetrofitService =
            retrofit.create(OpenWeatherMapRetrofitService::class.java)

    @Provides
    fun providesRemoteForecastDataStore(openWeatherMapRetrofitService: OpenWeatherMapRetrofitService) =
            RemoteForecastDataStore(openWeatherMapRetrofitService)
}