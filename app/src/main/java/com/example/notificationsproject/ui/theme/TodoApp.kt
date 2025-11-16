package com.example.notificationsproject.ui.theme


import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp

class TodoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        Log.d("APP_INIT", "Application started and Firebase initialized")
    }
}
