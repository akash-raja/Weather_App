package com.example.weatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            initializeNavHostFragment();
        }
    }

    private fun initializeNavHostFragment() {
        val finalHost = NavHostFragment.create(R.navigation.weather_app_navigation)
        supportFragmentManager.beginTransaction().add(R.id.weather_app_container, finalHost).setPrimaryNavigationFragment(finalHost).commit()
    }
}
