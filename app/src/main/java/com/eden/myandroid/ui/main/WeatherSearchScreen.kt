package com.eden.myandroid.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.eden.myandroid.viewmodel.CityViewModel
import com.eden.myandroid.viewmodel.WeatherViewModel
import com.eden.myandroid.viewmodel.auth.AuthViewModel
import com.eden.myandroid.navigation.Screen
@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherSearchScreen(
    navController: NavController,
    cityViewModel: CityViewModel,
    weatherViewModel: WeatherViewModel,
    paddingValues: PaddingValues
) {
    var city by remember { mutableStateOf(TextFieldValue("")) }
    val suggestions by cityViewModel.citySuggestions.collectAsState()
    val weather by weatherViewModel.weather.collectAsState()

        Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            OutlinedTextField(
                value = city.text,
                onValueChange = {
                    city = TextFieldValue(it)
                    cityViewModel.fetchCitySuggestions(it)
                },
                label = { Text("Enter city") },
                modifier = Modifier.fillMaxWidth()
            )

            LazyColumn(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                items(suggestions) { suggestion ->
                    TextButton(onClick = {
                        city = TextFieldValue(suggestion.substringBefore(","))
                        weatherViewModel.fetchWeather(suggestion.substringBefore(","))
                    }) {
                        Text(suggestion)
                    }
                }
            }

            Button(
                onClick = { weatherViewModel.fetchWeather(city.text.substringBefore(",")) },
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            ) {
                Text("Get Weather")
            }

            weather?.let {
                Card(modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("City: ${it.city}", style = MaterialTheme.typography.titleLarge)
                        Text("Temperature: ${it.temperature}°C", style = MaterialTheme.typography.bodyMedium)
                        Text("Wind Speed: ${it.windSpeed} km/h", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }

