package com.example.weatherapp.weatherinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.uistate.WeatherUIState
import kotlinx.android.synthetic.main.fragment_weather.*

class WeatherFragment : Fragment() {

    private lateinit var viewModel: WeatherFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, WeatherFragmentViewModelFactory()).get(WeatherFragmentViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val forecastAdapter = WeatherForecastAdapter(context!!)
        recycler_view_forecast.adapter = forecastAdapter

        viewModel.requestWeatherInformation()

        viewModel.uiState.observe(this, Observer { event ->
            event?.getContentIfNotHandled()?.let { uiState ->
                when (uiState) {
                    WeatherUIState.Loading -> {
                        loading_image.visibility = View.VISIBLE
                        loading_image.startAnimation(AnimationUtils.loadAnimation(context, R.anim.rotate))
                    }
                    is WeatherUIState.RequestSuccess -> {
                        clearAnimation()
                        loading_image.visibility = View.GONE

                        animateUp(recycler_view_forecast)

                        current_temperature.text = "${uiState.weatherData.currentTemperature}\u00B0"
                        location.text = uiState.weatherData.location
                        forecastAdapter.setItems(uiState.weatherData.forecastList)
                    }
                    WeatherUIState.Error -> {
                        clearAnimation()
                        loading_image.visibility = View.GONE
                        findNavController(this).navigate(R.id.action_weather_fragment_to_error_fragment)
                    }
                }
            }

        })
    }

    private fun clearAnimation() {
        loading_image.clearAnimation()
    }

    private fun animateUp(view: View) {
        recycler_view_forecast.visibility = View.VISIBLE
        val animate = TranslateAnimation(0f, 0f, view.height.toFloat(), 0f)
        animate.duration = 1000
        animate.fillAfter = true
        view.startAnimation(animate)
    }
}
