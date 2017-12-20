package com.paulpatena.weather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)


        var listView = findViewById<ListView>(R.id.forecastListView)

        var randomThings = arrayOf("Paul", "Mason", "Christina", "Craig")
        var myAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, randomThings)

        listView.adapter = myAdapter

        var retriever = WeatherRetriever()

        val callback = object : Callback<List<Forecast>> {
            override fun onResponse(call: Call<List<Forecast>>?, response: Response<List<Forecast>>?) {
                println("We got a response")
//                println(response?.body())
                for (forecastDay in response?.body()!!) {
                    println("High: ${forecastDay.high} Low: ${forecastDay.low}")
                }
            }

            override fun onFailure(call: Call<List<Forecast>>?, t: Throwable?) {
                println("API failed")
            }

        }

        retriever.getForecast(callback)

    }
}
