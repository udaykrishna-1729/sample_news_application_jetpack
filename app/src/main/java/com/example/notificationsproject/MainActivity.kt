package com.example.notificationsproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.notificationsproject.ui.theme.NotificationsProjectTheme


import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.notificationsproject.dao.NewsRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {

    private val repo = NewsRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Fetch immediately on app launch
        CoroutineScope(Dispatchers.IO).launch {
            repo.fetchAndPushNews()
        }

        // Background worker
        startWorker()

        // Realtime listener
        listenToNews()

        setContent {
            Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                Box(Modifier.fillMaxSize(), Alignment.Center) {
                    Text("News App Running…")
                }
            }
        }
    }

    private fun startWorker() {
        val request = PeriodicWorkRequestBuilder<NewsSyncWorker>(15, TimeUnit.SECONDS).build()

        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork("news_worker", ExistingPeriodicWorkPolicy.KEEP, request)
    }

    private fun  listenToNews() {
        FirebaseFirestore.getInstance()
            .collection("latest_news")
            .document("top")
            .addSnapshotListener { snapshot, _ ->

                if (snapshot == null || !snapshot.exists()) return@addSnapshotListener

                lifecycleScope.launch {
                    NotificationUtils.showSmallImageNotificationSuspend(
                        context = this@MainActivity,
                        title = snapshot.getString("title") ?: "",
                        description = snapshot.getString("description") ?: "",
                        imageUrl = snapshot.getString("image_url"),
                        articleUrl = snapshot.getString("link") ?: "",
                        published = snapshot.getString("pubDate") ?: ""
                    )
                }

            }
    }

}
