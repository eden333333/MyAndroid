package com.eden.myandroid.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.eden.myandroid.ui.auth.SignInScreen
import com.eden.myandroid.ui.auth.SignUpScreen
import com.eden.myandroid.ui.main.WeatherSearchScreen
import com.eden.myandroid.ui.main.FeedScreen
import com.eden.myandroid.ui.main.ProfileScreen
import com.eden.myandroid.ui.components.AppScaffold
import com.eden.myandroid.viewmodel.CityViewModel
import com.eden.myandroid.viewmodel.WeatherViewModel
import com.eden.myandroid.viewmodel.auth.AuthViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    cityViewModel: CityViewModel,
    weatherViewModel: WeatherViewModel
) {
    NavHost(navController = navController, startDestination = Screen.SignIn.route) {
        // Auth Screens (No Navbar)
        composable(Screen.SignIn.route) { SignInScreen(navController, authViewModel) }
        composable(Screen.SignUp.route) { SignUpScreen(navController, authViewModel) }

        // Wrapped Screens (with Navbar)
        composable(Screen.Weather.route) {
            AppScaffold(navController, authViewModel) { paddingValues ->
                WeatherSearchScreen(navController, cityViewModel, weatherViewModel,paddingValues)
            }
        }

        composable(Screen.Feed.route) {
            AppScaffold(navController, authViewModel) { paddingValues ->
                FeedScreen(navController, paddingValues)
            }
        }

        composable(Screen.Profile.route) {
            AppScaffold(navController, authViewModel) { paddingValues ->
                ProfileScreen(navController, paddingValues)
            }
        }
    }
}
