package com.eden.myandroid.data.repository

import com.eden.myandroid.data.remote.LocationIQApiService
import com.eden.myandroid.BuildConfig
import android.util.Log

class CityRepository(private val apiService: LocationIQApiService) {

    private val apiKey = "pk.b90e2df3364388c588d8117b889448c9"

    suspend fun getCitySuggestions(query: String): List<String> {
        return try {
            val suggestions = apiService.getCitySuggestions(apiKey, query).map { it.display_name }
            Log.d("CityRepository", "✅ Suggestions: $suggestions")
            suggestions
        } catch (e: Exception) {
            Log.e("CityRepository", "Error fetching suggestions: ${e.message}", e)
            emptyList()
        }
    }
}
