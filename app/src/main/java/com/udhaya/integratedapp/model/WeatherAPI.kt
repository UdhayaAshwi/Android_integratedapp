package com.udhaya.integratedapp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/* Interface in which the method that performs
*  HTTP request is declared
*/
interface WeatherAPI {
    @GET("data/2.5/weather?")
    fun getCurrentWeatherData(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("APPID") appId: String
    ): Call<WeatherResponse>
}