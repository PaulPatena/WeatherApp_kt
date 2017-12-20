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
        var retriever = WeatherRetriever()

        val callback = object : Callback<Weather> {
            override fun onResponse(call: Call<Weather>?, response: Response<Weather>?) {
                println("its working")
//                println(response?.body()?.query?.results?.channel?.title)
                title = response?.body()?.query?.results?.channel?.title
//                println(response?.body()?.query?.results?.channel?.item?.forecast)
                var forecastStrList = ArrayList<String>()
                var forecasts = response?.body()?.query?.results?.channel?.item?.forecast
                val degreesStr : Char = 0x00B0.toChar()
                if (forecasts != null) {
                    for (f in forecasts) {
                        val entry : String = "${f.date}, ${f.day}, High ${f.high}${degreesStr}C  " +
                                "Low ${f.low}${degreesStr}C ${f.text}"
                        println(entry)
                        forecastStrList.add(entry)
                    }
                }
                var myAdapter = ArrayAdapter(this@ForecastActivity, android.R.layout.simple_list_item_1, forecastStrList)
                listView.adapter = myAdapter

            }

            override fun onFailure(call: Call<Weather>?, t: Throwable?) {
                println("failed not working")
            }
        }

        val searchTerm = intent.extras.getString("searchTerm")
//        println("searchterm = $searchTerm")
        retriever.getForecast(callback, searchTerm)

    }
}
