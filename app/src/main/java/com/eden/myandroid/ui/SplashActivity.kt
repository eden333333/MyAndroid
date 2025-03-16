package com.eden.myandroid.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.eden.myandroid.MainActivity
import com.eden.myandroid.data.FirebaseAuthHelper

class SplashActivity : ComponentActivity() {
    private val authHelper = FirebaseAuthHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if user is logged in
        val currentUser = authHelper.getCurrentUser()

        if (currentUser != null) {
            // If user is logged in, go to MainActivity (Weather screen)
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            // If not logged in, go to LoginActivity
            startActivity(Intent(this, LoginActivity::class.java))
        }

        finish() // Close SplashActivity
    }
}
