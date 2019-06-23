package com.example.weatherapp.weatherinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.Event
import com.example.weatherapp.api.GetDataService
import com.example.weatherapp.model.WeatherDetails
import com.example.weatherapp.uistate.Forecast
import com.example.weatherapp.uistate.WeatherData
import com.example.weatherapp.uistate.WeatherUIState
import com.example.weatherapp.utils.getDay
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class WeatherFragmentViewModel(private val getDataService: GetDataService, private val scheduler: Scheduler = Schedulers.io()) :
    ViewModel() {

    private lateinit var disposable: Disposable
    val uiState = MutableLiveData<Event<WeatherUIState>>()

    fun requestWeatherInformation() {
        uiState.value = Event(WeatherUIState.Loading)
        makeRequest(getDataService)
    }

    private fun makeRequest(getDataService: GetDataService) {

        disposable = getDataService.getWeatherUpdate(BuildConfig.ApiKey)
            .subscribeOn(scheduler)
            .subscribe(
                { modelDataForUI(it) }, { uiState.postValue(Event(WeatherUIState.Error)) })
    }

    private fun modelDataForUI(body: WeatherDetails) {
        val weatherData = WeatherData(body.location.name, body.current.currentTemperature, body.forecast.forecastDay.map {
            Forecast(getDay(it.date), it.day.avgtempC)
        })
        uiState.postValue(Event(WeatherUIState.RequestSuccess(weatherData)))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}