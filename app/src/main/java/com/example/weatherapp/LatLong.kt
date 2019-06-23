package com.example.weatherapp

object LatLong {

    var lat = "51.508530"
        private set

    var long = "-0.076132"
        private set

    fun initialize(lat: Double, long: Double) {
        this.lat = lat.toString()
        this.long = long.toString()
    }

    fun getLocation() = "${this.lat},${this.long}"
}