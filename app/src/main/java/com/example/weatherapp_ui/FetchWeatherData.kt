package com.example.weatherapp_ui

import org.example.City
import org.example.Geocoder
import org.example.WeatherAPI
import org.example.WeatherData

class FetchWeatherData {
    suspend fun fetchWeatherData(city: String): WeatherData? {
        val geocoder = Geocoder()
        val weatherAPI = WeatherAPI()

        val coordinates = geocoder.getCoordinates(city)

        if (coordinates != null){
            val weatherData = weatherAPI.retrieveWeatherData(coordinates.latitude, coordinates.longitude)
            if (weatherData !=null){
                return weatherData
            }
            return null
        }
        return null
    }
}