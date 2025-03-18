package com.eden.myandroid.viewmodel.auth

import androidx.lifecycle.ViewModel
import com.eden.myandroid.data.repository.AuthRepository
class AuthViewModel(private val repository: AuthRepository) : ViewModel() {
    fun signUp(
        email: String,
        password: String,
        username: String,
        firstName: String,
        lastName: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        repository.signUp(email, password, username, firstName, lastName, onResult)
    }

    fun signIn(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        repository.signIn(email, password, onResult)
    }

    fun signOut() {
        repository.signOut()
    }
}