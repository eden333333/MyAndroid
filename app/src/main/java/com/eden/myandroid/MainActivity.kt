package com.eden.myandroid
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.eden.myandroid.data.remote.LocationIQApiService
import com.eden.myandroid.data.repository.AuthRepository
import com.eden.myandroid.data.repository.CityRepository
import com.eden.myandroid.data.repository.WeatherRepository
import com.eden.myandroid.navigation.NavGraph
import com.eden.myandroid.viewmodel.auth.AuthViewModel
import com.eden.myandroid.viewmodel.CityViewModelFactory
import com.eden.myandroid.viewmodel.WeatherViewModel
import com.eden.myandroid.viewmodel.auth.AuthViewModelFactory
import com.eden.myandroid.viewmodel.CityViewModel
import com.eden.myandroid.viewmodel.WeatherViewModelFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val authRepository = remember { AuthRepository() }
            val cityRepository = remember { CityRepository(LocationIQApiService.create()) }
            val weatherRepository = remember { WeatherRepository() }

            val navController = rememberNavController()
            val authViewModel: AuthViewModel =
                viewModel(factory = AuthViewModelFactory(authRepository))
            val cityViewModel: CityViewModel =
                viewModel(factory = CityViewModelFactory(cityRepository))
            val weatherViewModel: WeatherViewModel =
                viewModel(factory = WeatherViewModelFactory(weatherRepository))

            //  Navigation
            NavGraph(
                navController = navController,
                authViewModel = authViewModel,
                cityViewModel = cityViewModel,
                weatherViewModel = weatherViewModel)
        }
    }
}