package com.eden.myandroid.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun FeedScreen(navController: NavController, paddingValues: PaddingValues) {
    Column(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
        Text("Feed Screen", style = MaterialTheme.typography.headlineMedium)
    }
}