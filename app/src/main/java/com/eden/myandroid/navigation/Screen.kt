package com.eden.myandroid.navigation

sealed class Screen (val route: String){
    object SignIn: Screen("sign_in")
    object SignUp: Screen("sign_up")
    object Weather: Screen("weather_search") // main screen
    object Feed : Screen("feed")
    object Profile : Screen("profile")
}