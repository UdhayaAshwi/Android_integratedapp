package com.udhaya.integratedapp.model

import com.google.gson.annotations.SerializedName

/* A Model POJO class for the response object
that is used to map the response parameters to
 their respective variables.
 */
data class WeatherResponse(

    @SerializedName("coord")
    var coord: Coord? = null,
    @SerializedName("sys")
    var sys: Sys? = null,
    @SerializedName("weather")
    var weather: ArrayList<Weather>,
    @SerializedName("main")
    var main: Main? = null,
    @SerializedName("wind")
    var wind: Wind? = null,
    @SerializedName("rain")
    var rain: Rain? = null,
    @SerializedName("clouds")
    var clouds: Clouds? = null,
    @SerializedName("dt")
    var dt: Float = 0.toFloat(),
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("cod")
    var cod: Float = 0.toFloat()
)

data class Weather(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("main")
    var main: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("icon")
    var icon: String? = null
)

data class Clouds(
    @SerializedName("all")
    var all: Float = 0.toFloat()
)

data class Rain(
    @SerializedName("3h")
    var h3: Float = 0.toFloat()
)

data class Wind(
    @SerializedName("speed")
    var speed: Float = 0.toFloat(),
    @SerializedName("deg")
    var deg: Float = 0.toFloat()
)

data class Main(
    @SerializedName("temp")
    var temp: Float = 0.toFloat(),
    @SerializedName("humidity")
    var humidity: Float = 0.toFloat(),
    @SerializedName("pressure")
    var pressure: Float = 0.toFloat(),
    @SerializedName("temp_min")
    var tempMin: Float = 0.toFloat(),
    @SerializedName("temp_max")
    var tempMax: Float = 0.toFloat()
)

class Sys {
    @SerializedName("country")
    var country: String? = null

    @SerializedName("sunrise")
    var sunRise: Long = 0

    @SerializedName("sunset")
    var sunSet: Long = 0
}

class Coord {
    @SerializedName("lon")
    var lon: Float = 0.toFloat()

    @SerializedName("lat")
    var lat: Float = 0.toFloat()
}