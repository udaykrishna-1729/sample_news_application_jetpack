package com.example.notificationsproject.dao



import android.util.Log
import com.example.notificationsproject.RetrofitInstance
import com.google.firebase.firestore.FirebaseFirestore

class NewsRepository {

    private val firestore = FirebaseFirestore.getInstance()

    suspend fun fetchAndPushNews() {
        try {
            Log.d("NEWS_REPO", "Fetching news...")

            val response = RetrofitInstance.api.getTopHeadlines("pub_9317a15901154e60b27e0ce17528429c")

            Log.d("NEWS_RESPONSE", response.toString())

            val latest = response.results?.firstOrNull()

            if (latest == null) {
                Log.e("NEWS_REPO", "NO ARTICLES FOUND or API ERROR")
                return
            }

            firestore.collection("latest_news")
                .document("top")
                .set(latest)
                .addOnSuccessListener {
                    Log.d("NEWS_REPO", "Uploaded to Firestore")
                }
                .addOnFailureListener {
                    Log.e("NEWS_REPO", "Failed to upload", it)
                }

        } catch (e: Exception) {
            Log.e("NEWS_REPO", "API Error", e)
        }
    }
}

