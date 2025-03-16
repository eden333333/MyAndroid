package com.eden.myandroid

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eden.myandroid.ui.WeatherSearchScreen
import com.eden.myandroid.data.remote.LocationIQApiService
import com.eden.myandroid.data.repository.CityRepository
import com.eden.myandroid.data.repository.WeatherRepository
import com.eden.myandroid.viewmodel.CityViewModel
import com.eden.myandroid.viewmodel.CityViewModelFactory
import com.eden.myandroid.viewmodel.WeatherViewModel
import com.eden.myandroid.viewmodel.WeatherViewModelFactory

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cityRepository = CityRepository(LocationIQApiService.create())
        val weatherRepository = WeatherRepository()

        setContent {
            val cityViewModel: CityViewModel = viewModel(factory = CityViewModelFactory(cityRepository))
            val weatherViewModel: WeatherViewModel = viewModel(factory = WeatherViewModelFactory(weatherRepository))

            Scaffold(modifier = Modifier.fillMaxSize()) {
                WeatherSearchScreen(cityViewModel, weatherViewModel)
            }
        }
    }
}
