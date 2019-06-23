package com.example.weatherapp.weatherinfo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.uistate.Forecast
import kotlinx.android.synthetic.main.weather_forecast_item.view.*

class WeatherForecastAdapter(private val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    private var list = listOf<Forecast>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.weather_forecast_item, parent, false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.temperature.text = list[position].averageTemperature.toString()
        holder.day.text = list[position].day
    }

    fun setItems(newList: List<Forecast>) {
        list = newList
        notifyDataSetChanged()
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val day: TextView = view.forecast_day
    val temperature: TextView = view.forecast_temperature
}