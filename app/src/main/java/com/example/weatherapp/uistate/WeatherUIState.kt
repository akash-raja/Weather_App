package com.example.weatherapp.uistate

data class Forecast(val day: String, val averageTemperature: Double)
data class WeatherData(val location: String, val currentTemperature: Double, val forecastList: List<Forecast>)

sealed class WeatherUIState {
    object Loading : WeatherUIState()
    class RequestSuccess(val weatherData: WeatherData) : WeatherUIState()
    object Error : WeatherUIState()
}