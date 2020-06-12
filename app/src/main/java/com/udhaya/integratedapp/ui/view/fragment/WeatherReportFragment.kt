package com.udhaya.integratedapp.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.udhaya.integratedapp.R
import com.udhaya.integratedapp.model.WeatherAPI
import com.udhaya.integratedapp.model.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/* Fragment to perform API calls
* */
class WeatherReportFragment : Fragment() {
    private var weatherData: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherData = view.findViewById(R.id.textview)
        view.findViewById<View>(R.id.button)?.setOnClickListener { getCurrentData() }
    }

    internal fun getCurrentData() {
// get the current weather data using API call
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherAPI::class.java)
        val call = service.getCurrentWeatherData(latitude, longitude, appId)
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.code() == 200) {
                    val weatherResponse = response.body()!!

                    val stringBuilder = "Country: " +
                            weatherResponse.sys!!.country +
                            "\n" +
                            "Temperature: " +
                            weatherResponse.main!!.temp +
                            "\n" +
                            "Temperature(Min): " +
                            weatherResponse.main!!.tempMin +
                            "\n" +
                            "Temperature(Max): " +
                            weatherResponse.main!!.tempMax +
                            "\n" +
                            "Humidity: " +
                            weatherResponse.main!!.humidity +
                            "\n" +
                            "Pressure: " +
                            weatherResponse.main!!.pressure

                    weatherData!!.text = stringBuilder
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                weatherData!!.text = t.message
            }
        })
    }

    companion object {
        var baseUrl = "http://api.openweathermap.org/"
        var appId = "2e65127e909e178d0af311a81f39948c"
        var latitude = "35"
        var longitude = "139"
    }
}

