package com.example.notificationsproject


/*

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.notificationsproject.R
import com.example.notificationsproject.dao.NewsRepository
import kotlinx.coroutines.*

class NewsForegroundService : Service(), CoroutineScope {

    private val POLL_PERIOD = 10_000L // 10 seconds
    private val serviceJob = SupervisorJob()

    override val coroutineContext = Dispatchers.Default + serviceJob

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        createChannel()

        startForeground(
            44,
            buildServiceNotification()
        )

        launch {
            val repo = NewsRepository()
            while (isActive) {
                val article = repo.fetchLatestArticle()
                if (article != null) {
                    NotificationUtils.showSmallImageNotificationSuspend(
                        context = applicationContext,
                        title = article.title ?: "News",
                        description = article.description ?: "",
                        imageUrl = article.image_url,
                        articleUrl = article.link ?: ""
                    )
                }
                delay(POLL_PERIOD)
            }
        }

        return START_STICKY
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            val nm = getSystemService(NotificationManager::class.java)
            nm.createNotificationChannel(
                NotificationChannel(
                    "foreground_poll_channel",
                    "News Polling Service",
                    NotificationManager.IMPORTANCE_LOW
                )
            )
        }
    }

    private fun buildServiceNotification(): Notification {
        val pi = PendingIntent.getActivity(
            this,
            1,
            Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, "foreground_poll_channel")
            .setContentTitle("News Polling Active")
            .setContentText("Fetching new news every 10 seconds")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pi)
            .build()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancel()
    }
}
*/
