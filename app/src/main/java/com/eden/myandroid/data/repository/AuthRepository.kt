package com.eden.myandroid.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class AuthRepository {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun signUp(
        email: String,
        password: String,
        username: String,
        firstName: String,
        lastName: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid
                    if (userId != null) {
                        val user = hashMapOf(
                            "username" to username,
                            "firstName" to firstName,
                            "lastName" to lastName,
                            "email" to email
                        )
                        db.collection("users").document(userId)
                            .set(user)
                            .addOnSuccessListener { onResult(true, null) }
                            .addOnFailureListener {
                                onResult(false, "Failed to save user data: ${it.localizedMessage}")
                            }
                    } else {
                        onResult(false, "Failed to retrieve user ID after sign-up.")
                    }
                } else {
                    val errorMessage = when (task.exception) {
                        is com.google.firebase.auth.FirebaseAuthWeakPasswordException -> "Password is too weak. Please choose a stronger password."
                        is com.google.firebase.auth.FirebaseAuthInvalidCredentialsException -> "Invalid email format. Please enter a valid email."
                        is com.google.firebase.auth.FirebaseAuthUserCollisionException -> "An account with this email already exists."
                        else -> "Sign-up failed: ${task.exception?.localizedMessage ?: "Unknown error"}"
                    }
                    onResult(false, errorMessage)
                }
            }
    }

    fun signIn(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null)
                } else {
                    val errorMessage = when (task.exception) {
                        is com.google.firebase.auth.FirebaseAuthInvalidUserException -> "Email or password is incorrect. Please try again."
                        is com.google.firebase.auth.FirebaseAuthInvalidCredentialsException -> "Email or password is incorrect. Please try again."
                        else -> "Sign-in failed: ${task.exception?.localizedMessage ?: "Unknown error"}"
                    }
                    onResult(false, errorMessage)
                }
            }
    }

    fun signOut() {
        auth.signOut()
    }
}
