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

        val callback = object : Callback<Weather> {
            override fun onResponse(call: Call<Weather>?, response: Response<Weather>?) {
                println("its working")
                println(response?.body()?.query?.results?.channel?.title)
                title = response?.body()?.query?.results?.channel?.title
            }

            override fun onFailure(call: Call<Weather>?, t: Throwable?) {
                println("failed not working")
            }
        }

        retriever.getForecast(callback)

    }
}
