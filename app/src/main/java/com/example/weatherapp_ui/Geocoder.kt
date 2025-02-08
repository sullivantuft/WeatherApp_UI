package org.example

import org.json.JSONArray

//This class is responsible for converting a city name into longitude and latitude coordinates. This is done using the OpenStreetMap API
class Geocoder {
    // This function gets the coordinates of a given city
    fun getCoordinates(city: String): City? {
        val requests = apiRequests() // Initializes new request object

        val url = "https://nominatim.openstreetmap.org/search?q=$city&format=json" // OpenStreetMap API URL
        val jsonResponse = requests.sendRequest(url) // Sends a request and gets a JSON response as a string

        return if (jsonResponse != null) {
            parseCoordinates(jsonResponse) // If the JSON Response is not null, return the coordinates
        } else {
            null
        }
    }

    // This function parses the JSON response and returns the coordinates as a City object
    private fun parseCoordinates(jsonResponse: String): City? {
        return try {
            val jsonArray = JSONArray(jsonResponse) // Creates an array from the response string

            if (jsonArray.length() > 0) {
                val firstResult = jsonArray.getJSONObject(0) // Uses the first result from the JSON response
                val latitude = firstResult.getString("lat").toDouble() // Convert lat from String to Double
                val longitude = firstResult.getString("lon").toDouble() // Convert lon from String to Double

                // Return the first result (most relevant) with latitude and longitude
                City(firstResult.getString("name"), latitude, longitude)
            } else {
                println("No results found")
                null
            }
        } catch (e: Exception) {
            println("Error parsing response: ${e.message}") // Catch any errors during parsing
            null
        }
    }
}
