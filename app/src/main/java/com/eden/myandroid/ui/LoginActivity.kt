package com.eden.myandroid.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eden.myandroid.MainActivity
import com.eden.myandroid.data.FirebaseAuthHelper
import com.eden.myandroid.ui.theme.MyAndroidTheme

class LoginActivity : ComponentActivity() {
    private val authHelper = FirebaseAuthHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAndroidTheme {
                LoginScreen(
                    onLogin = { email, password ->
                        if (email.isBlank() || password.isBlank()) {
                            Toast.makeText(this, "Email and Password cannot be empty", Toast.LENGTH_SHORT).show()
                        } else {
                            authHelper.loginUser(email, password) { success, error ->
                                if (success) {
                                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                                    // Navigate to MainActivity
                                    startActivity(Intent(this, MainActivity::class.java))
                                    finish()
                                } else {
                                    Toast.makeText(this, error ?: "Login Failed", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    },
                    onRegister = { email, password ->
                        if (email.isBlank() || password.isBlank()) {
                            Toast.makeText(this, "Email and Password cannot be empty", Toast.LENGTH_SHORT).show()
                        } else {
                            authHelper.registerUser(email, password) { success, error ->
                                if (success) {
                                    Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                                } else {
                                    Toast.makeText(this, error ?: "Registration Failed", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun LoginScreen(
    onLogin: (String, String) -> Unit,
    onRegister: (String, String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Email input field with validation
        OutlinedTextField(
            value = email,
            onValueChange = { email = it.trim() }, // Trim whitespace
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            isError = !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty() // Show error for invalid emails
        )
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()) {
            Text(
                text = "Invalid email format",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Password input field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Login button
        Button(
            onClick = { onLogin(email, password) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Register button
        Button(
            onClick = { onRegister(email, password) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Register")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MyAndroidTheme {
        LoginScreen(
            onLogin = { _, _ -> },
            onRegister = { _, _ -> }
        )
    }
}
