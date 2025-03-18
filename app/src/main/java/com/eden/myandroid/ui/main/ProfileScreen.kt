package com.eden.myandroid.ui.main
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navController: NavController, paddingValues: PaddingValues) {
    Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
        Text("Profile Screen", style = MaterialTheme.typography.headlineMedium)
    }
}