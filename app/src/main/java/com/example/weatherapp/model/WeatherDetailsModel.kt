package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

data class WeatherDetails(@SerializedName("location") var location: Location, @SerializedName("current") var current: Current, @SerializedName("forecast") var forecast: Forecast)

data class Location(@SerializedName("name") var name: String)

data class Current(@SerializedName("temp_c") var currentTemperature: Double)

data class Forecast(@SerializedName("forecastday") var forecastDay: List<ForecastDay>)

data class ForecastDay(@SerializedName("date") var date: String, @SerializedName("date_epoch") var dateEpoch: Int, @SerializedName("day") var day: Day)

data class Day(@SerializedName("avgtemp_c") var avgtempC: Double)


