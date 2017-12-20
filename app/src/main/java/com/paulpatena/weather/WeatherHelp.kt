package com.paulpatena.weather

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by pmp on 20/12/2017.
 */

interface WeatherAPI {
    @GET("bins/1g4ion")
    fun getForecast(): Call<List<Forecast>>
}

class Forecast(val high: String, val low: String)

class WeatherRetriever {
    val service : WeatherAPI

    init {
        val retrofit = Retrofit.Builder().baseUrl("https://api.myjson.com/").addConverterFactory(GsonConverterFactory.create()).build()
        service = retrofit.create(WeatherAPI::class.java)
    }

    fun getForecast(callback: Callback<List<Forecast>>) {
        val call = service.getForecast()
        call.enqueue(callback)  // run this command async
    }
}
