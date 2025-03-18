package com.eden.myandroid.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.eden.myandroid.navigation.Screen
import com.eden.myandroid.viewmodel.auth.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(navController: NavController, authViewModel: AuthViewModel, content: @Composable (PaddingValues) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color(0xFF4267B2)),
                modifier = Modifier.fillMaxWidth(),
                actions = {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                        TextButton(onClick = { navController.navigate(Screen.Weather.route) }) {
                            Text("Weather", color = Color.White)
                        }
                        TextButton(onClick = { navController.navigate(Screen.Feed.route) }) {
                            Text("Feed", color = Color.White)
                        }
                        TextButton(onClick = { navController.navigate(Screen.Profile.route) }) {
                            Text("Profile", color = Color.White)
                        }
                        TextButton(onClick = {
                            authViewModel.signOut()
                            navController.navigate(Screen.SignIn.route) { popUpTo(0) }
                        }) {
                            Text("Logout", color = Color.White)
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        content(innerPadding)
    }
}
