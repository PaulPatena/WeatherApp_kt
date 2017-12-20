package com.paulpatena.weather

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var getForecast = findViewById<Button>(R.id.button)

        getForecast.setOnClickListener {
            var moveToForecastActivity =  Intent(this, ForecastActivity::class.java)
            val searchEditText = findViewById<EditText>(R.id.searchText)
            moveToForecastActivity.putExtra("searchTerm", searchEditText.text.toString())
            startActivity(moveToForecastActivity)
        }
    }
}
