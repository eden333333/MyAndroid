package com.eden.myandroid.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
//import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eden.myandroid.data.FirebaseAuthHelper
import com.eden.myandroid.ui.ui.theme.MyAndroidTheme
import com.google.firebase.auth.UserProfileChangeRequest

class RegisterActivity : ComponentActivity() {
    private val authHelper = FirebaseAuthHelper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            MyAndroidTheme {
                RegisterScreen(

                    onRegister = {firstName, lastName, email, password ->
                        if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || password.isBlank()) {
                            Toast.makeText(this, "ALl fields must be filled", Toast.LENGTH_SHORT).show()
                        } else {
                            authHelper.registerUser(firstName, lastName, email, password) { success, error ->
                                if (success) {
                                    Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                                } else {
                                    Toast.makeText(this, error ?: "Registration Failed", Toast.LENGTH_SHORT).show()
                                }
                            }

                        }
                    }
                )

            } // איך לעדכן את פייר בייס auth עם שם פרטי ושם משפחה בזמן רישום
        }
    }
}

@Composable
fun RegisterScreen( onRegister: (String, String, String, String) -> Unit) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // first name input field with validation
        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it.trim() }, // Trim whitespace
            label = { Text("First name") },
            modifier = Modifier.fillMaxWidth(),
            isError = firstName.isNotEmpty() // Show error for invalid emails
        )
        if (firstName.isNotEmpty()) {
            Text(
                text = "First name cannot be empty",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
        // Lastname input field with validation
        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it.trim() }, // Trim whitespace
            label = { Text("Last name") },
            modifier = Modifier.fillMaxWidth(),
            isError = lastName.isNotEmpty() // Show error for invalid emails
        )
        if (lastName.isNotEmpty()) {
            Text(
                text = "Last name cannot be empty",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
        // Email input field with validation
        OutlinedTextField(
            value = email,
            onValueChange = { email = it.trim() }, // Trim whitespace
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            isError = !android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches() && email.isNotEmpty() // Show error for invalid emails
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
            onClick = { onRegister(firstName, lastName,email, password) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Register")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    MyAndroidTheme {
        RegisterScreen(
            onRegister = { _, _, _, _-> }
        )
    }
}