package com.example.weatherapp.api

import com.example.weatherapp.LatLong
import com.example.weatherapp.model.WeatherDetails
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

const val NO_OF_DAYS = "4"
const val BASE_URL = "https://api.apixu.com/v1/";

interface GetDataService {

    @GET("forecast.json")
    fun getWeatherUpdate(@Query("key") key: String, @Query("days") days: String = NO_OF_DAYS, @Query("q") latLong: String = LatLong.getLocation()): Observable<WeatherDetails>
}

//Test1
//Test2
//Test3