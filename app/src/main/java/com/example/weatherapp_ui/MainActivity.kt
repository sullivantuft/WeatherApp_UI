package com.example.weatherapp_ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*
import androidx.compose.ui.graphics.Color
import com.example.weatherapp_ui.FetchWeatherData
import org.example.CurrentWeather

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppUI()
        }
    }
}

// Composable function to build the UI for the weather app
@Composable
fun WeatherAppUI() {
    // Declare state variables to hold the city, weather data, and loading status
    var city by remember { mutableStateOf("") }
    var weatherData by remember { mutableStateOf<CurrentWeather?>(null)}
    var isLoading by remember { mutableStateOf(false) }

    // Layout structure using a Column to stack elements vertically
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp).background(Color(0xFFADD8E6)), // Fill the screen and add padding
        horizontalAlignment = Alignment.CenterHorizontally, // Center content horizontally
        verticalArrangement = Arrangement.Center // Center content vertically
    ) {
        // Display a text label asking for city input
        Text(text = "Enter City Name:", style = MaterialTheme.typography.headlineMedium)

        // TextField for entering the city name
        OutlinedTextField(
            value = city, // Bind the value of the TextField to the 'city' variable
            onValueChange = { city = it }, // Update 'city' when the input changes
            modifier = Modifier.fillMaxWidth().padding(8.dp), // Make it full width with padding
            label = { Text("City") } // Label for the TextField
        )

        // Button to trigger the weather request when clicked
        Button(
            onClick = {
                isLoading = true
                // Launch the network request in a coroutine on a background thread
                CoroutineScope(Dispatchers.IO).launch {
                    val getWeatherData = FetchWeatherData()
                    val response = getWeatherData.fetchWeatherData(city) // Fetch weather data for the entered city
                    withContext(Dispatchers.Main) { // Switch back to the main thread to update UI
                        if(response != null){
                            weatherData = response.currentWeather // Update the weather data
                            isLoading = false // Set loading to false after the request finishes
                        }
                    }
                }
            }
        ) {
            Text("Get Weather") // Button text
        }

        // Show a loading indicator while the weather data is being fetched
        if (isLoading) {
            CircularProgressIndicator() // Display a progress indicator when loading
        } else {
            // If there is weather data, display it
            weatherData?.let {
                Text(text = "Temperature: ${it.temperature}°F", style = MaterialTheme.typography.bodyLarge) // Display the temperature
                Text(text = "Precipitation: ${it.precipitation} inches", style = MaterialTheme.typography.bodyLarge) // Display precipitation
                Text(text = "Wind Speed: ${it.precipitation} mph", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}