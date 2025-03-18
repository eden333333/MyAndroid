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
                    if (userId != null) { // Save user data to Firestore
                        val user = hashMapOf(
                            "username" to username,
                            "firstName" to firstName,
                            "lastName" to lastName,
                            "email" to email
                        )
                        db.collection("users").document(userId)
                            .set(user)
                            .addOnSuccessListener { onResult(true, null) }
                            .addOnFailureListener { onResult(false, it.message) }
                    } else {
                        onResult(false, "User ID is null")
                    }
                } else {
                    onResult(false, task.exception?.message)
                }
            }
    }
    fun signIn(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null)
                } else {
                    onResult(false, task.exception?.message)
                }
            }
    }

    fun signOut() {
        auth.signOut()
    }

}