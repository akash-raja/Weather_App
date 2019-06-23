package com.example.weatherapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherapp.api.GetDataService
import com.example.weatherapp.model.Current
import com.example.weatherapp.model.Day
import com.example.weatherapp.model.Forecast
import com.example.weatherapp.model.ForecastDay
import com.example.weatherapp.model.Location
import com.example.weatherapp.model.WeatherDetails
import com.example.weatherapp.uistate.WeatherUIState
import com.example.weatherapp.weatherinfo.WeatherFragmentViewModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class WeatherFragmentViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getDataService: GetDataService

    private lateinit var weatherFragmentViewModel: WeatherFragmentViewModel

    private var schedulerProvider = Schedulers.trampoline()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        weatherFragmentViewModel = WeatherFragmentViewModel(getDataService, schedulerProvider)
    }

    @Test
    fun `Error ui state when there is an request error`() {

        Mockito.`when`(getDataService.getWeatherUpdate(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(Observable.error(Exception()))
        weatherFragmentViewModel.requestWeatherInformation()
        assertThat(weatherFragmentViewModel.uiState.value?.getContentIfNotHandled()).isEqualTo(WeatherUIState.Error)
    }

    @Test
    fun `Success ui state when request is successful`() {
        val forecastDay = ForecastDay("12", 1, Day(23.1))
        val weatherDetails = WeatherDetails(Location("v"), Current(12.2), Forecast(listOf(forecastDay)))
        Mockito.`when`(getDataService.getWeatherUpdate(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(Observable.just(weatherDetails))

        weatherFragmentViewModel.requestWeatherInformation()

        assertThat(weatherFragmentViewModel.uiState.value?.getContentIfNotHandled() is WeatherUIState.RequestSuccess).isTrue()
    }
}
