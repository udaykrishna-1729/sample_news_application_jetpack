package com.example.notificationsproject



/*
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.notificationsproject.R
import com.example.notificationsproject.ui.theme.NewsDetailActivity
import java.net.URL

fun showNewsNotification(
    context: Context,
    title: String,
    description: String,
    imageUrl: String?,
    fullArticle: String,
    publishedOn: String
) {
    val channelId = "news_big_image_channel"

    // Open detail screen with FULL NEWS DATA
    val intent = Intent(context, NewsDetailActivity::class.java).apply {
        putExtra("title", title)
        putExtra("description", description)
        putExtra("imageUrl", imageUrl)
        putExtra("published", publishedOn)
        putExtra("fullArticleUrl", fullArticle)
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
    }

    val pendingIntent = PendingIntent.getActivity(
        context,
        System.currentTimeMillis().toInt(),
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    // Load image for notification (optional)
    val bigImage = try {
        if (imageUrl != null)
            BitmapFactory.decodeStream(URL(imageUrl).openStream())
        else null
    } catch (e: Exception) { null }

    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle(title)
        .setContentText(description)
        .setAutoCancel(true)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setContentIntent(pendingIntent)

    if (bigImage != null) {
        builder.setStyle(
            NotificationCompat.BigPictureStyle()
                .bigPicture(bigImage)
                .bigLargeIcon(null as Bitmap?)
        )
    }

    val manager = context.getSystemService(NotificationManager::class.java)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        manager.createNotificationChannel(
            NotificationChannel(channelId, "News Alerts", NotificationManager.IMPORTANCE_HIGH)
        )
    }

    manager.notify((System.currentTimeMillis() % 10000).toInt(), builder.build())
}*/


//package com.example.notificationsproject.utils

/*import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.notificationsproject.R
import com.example.notificationsproject.ui.theme.NewsDetailActivity
import java.net.URL

fun showSmallImageNotification(
    context: Context,
    title: String,
    description: String,
    imageUrl: String?,
    articleUrl: String,
    published: String
) {
    val channelId = "news_small_image_channel"

    // Intent → open detail screen
    val intent = Intent(context, NewsDetailActivity::class.java).apply {
        putExtra("title", title)
        putExtra("description", description)
        putExtra("imageUrl", imageUrl)
        putExtra("published", published)
        putExtra("fullArticleUrl", articleUrl)
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
    }

    val pendingIntent = PendingIntent.getActivity(
        context,
        System.currentTimeMillis().toInt(),
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    // Download thumbnail (small image)
    val smallImage: Bitmap? = try {
        if (!imageUrl.isNullOrEmpty())
            BitmapFactory.decodeStream(URL(imageUrl).openStream())
        else null
    } catch (e: Exception) {
        null
    }

    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle(title)
        .setContentText(description)
        .setLargeIcon(smallImage)      // <-- Small thumbnail image
        .setStyle(NotificationCompat.BigTextStyle().bigText(description)) // Expand text
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .setPriority(NotificationCompat.PRIORITY_HIGH)

    val manager = context.getSystemService(NotificationManager::class.java)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            channelId,
            "News Notifications",
            NotificationManager.IMPORTANCE_HIGH
        )
        manager.createNotificationChannel(channel)
    }

    manager.notify((System.currentTimeMillis() % 100000).toInt(), builder.build())
}*/


/*

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.notificationsproject.R
import com.example.notificationsproject.ui.theme.NewsDetailActivity
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

object NotificationUtils {

    // Download image SAFELY (supports redirects, http, https)
    private fun loadImage(urlString: String?): Bitmap? {
        if (urlString.isNullOrEmpty()) return null

        return try {
            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection

            connection.connectTimeout = 5000
            connection.readTimeout = 5000
            connection.instanceFollowRedirects = true
            connection.doInput = true
            connection.connect()

            val input: InputStream = connection.inputStream
            BitmapFactory.decodeStream(input)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun showSmallImageNotification(
        context: Context,
        title: String,
        description: String,
        imageUrl: String?,
        articleUrl: String,
        published: String
    ) {
        val channelId = "news_small_image_channel"

        // Intent → detail page
        val intent = Intent(context, NewsDetailActivity::class.java).apply {
            putExtra("title", title)
            putExtra("description", description)
            putExtra("imageUrl", imageUrl)
            putExtra("published", published)
            putExtra("fullArticleUrl", articleUrl)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            System.currentTimeMillis().toInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Load small image (thumbnail)
        val smallImage = loadImage(imageUrl)

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(description)
            .setLargeIcon(smallImage)   // <-- small image here
            .setStyle(NotificationCompat.BigTextStyle().bigText(description))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val manager = context.getSystemService(NotificationManager::class.java)

        // Create channel for Android 8+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "News Notifications",
                NotificationManager.IMPORTANCE_HIGH
            )
            manager.createNotificationChannel(channel)
        }

        manager.notify((System.currentTimeMillis() % 100000).toInt(), builder.build())
    }
}
*/





/*
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.notificationsproject.ui.theme.NewsWebViewActivity

fun showNotification(context: Context, title: String, message: String, url: String) {
    val channelId = "news_channel"

    val intent = Intent(context, NewsWebViewActivity::class.java).apply {
        putExtra("news_url", url)   // <-- send the full article link
    }

    val pendingIntent = PendingIntent.getActivity(
        context,
        0,
        intent,
        PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
    )

    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle(title)
        .setContentText(message)
        .setAutoCancel(true)
        .setContentIntent(pendingIntent)

    val manager = context.getSystemService(NotificationManager::class.java)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        manager.createNotificationChannel(
            NotificationChannel(channelId, "News", NotificationManager.IMPORTANCE_HIGH)
        )
    }

    manager.notify(1, builder.build())
}*/

/*



import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.notificationsproject.R
import com.example.notificationsproject.ui.theme.NewsDetailActivity
import kotlinx.coroutines.runBlocking
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
*/

//object NotificationUtils {

    // Download & Resize Image Safely (Fix for TOI image CDN)
    /*private fun loadBitmapSafe(urlString: String?): Bitmap? {
        if (urlString.isNullOrEmpty()) return null

        return try {
            Log.d("IMAGE_URL", "Trying to load: $urlString")

            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection

            connection.apply {
                connectTimeout = 6000
                readTimeout = 6000
                instanceFollowRedirects = true
                doInput = true
                connect()
            }

            val input: InputStream = connection.inputStream
            val originalBitmap = BitmapFactory.decodeStream(input)
            input.close()

            if (originalBitmap == null) {
                Log.e("IMAGE_LOAD", "Bitmap decode returned NULL")
                return null
            }

            // Resize to avoid notification icon issues
            val newWidth = 300
            val aspect = originalBitmap.height.toFloat() / originalBitmap.width.toFloat()
            val newHeight = (newWidth * aspect).toInt()

            val resized = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true)
            Log.d("IMAGE_LOAD", "Loaded & resized bitmap successfully")
            resized

        } catch (e: Exception) {
            Log.e("IMAGE_LOAD", "Error loading bitmap: ${e.message}")
            null
        }
    }*/


    /*private fun loadBitmapSafe(urlString: String?): Bitmap? {
        if (urlString.isNullOrEmpty()) return null

        return try {
            Log.d("IMAGE_URL", "Trying to load: $urlString")

            val url = URL(urlString)
            val connection = url.openConnection() as HttpURLConnection

            connection.apply {
                setRequestProperty(
                    "User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                            "(KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
                )
                connectTimeout = 8000
                readTimeout = 8000
                instanceFollowRedirects = true
                doInput = true
                connect()
            }

            Log.d("DEBUG_IMG", "URL: $urlString")
            Log.d("DEBUG_IMG", "Response Code: ${connection.responseCode}")
            Log.d("DEBUG_IMG", "Content-Type: ${connection.contentType}")

            val input = connection.inputStream
            val bmp = BitmapFactory.decodeStream(input)
            input.close()

            if (bmp == null) {
                Log.e("IMAGE_LOAD", "Bitmap decode failed, returning NULL")
                return null
            }

            Log.d("IMAGE_LOAD", "Bitmap loaded successfully!")

            // Resize bitmap
            val newWidth = 300
            val aspect = bmp.height.toFloat() / bmp.width.toFloat()
            Bitmap.createScaledBitmap(bmp, newWidth, (newWidth * aspect).toInt(), true)

        } catch (e: Exception) {
            Log.e("IMAGE_LOAD", "Error: ${e.message}")
            null
        }
    }


    fun showSmallImageNotification(
        context: Context,
        title: String,
        description: String,
        imageUrl: String?,
        articleUrl: String,
        published: String
    ) {
        val channelId = "news_small_image_channel"

        // Open NewsDetailActivity on click
        val intent = Intent(context, NewsDetailActivity::class.java).apply {
            putExtra("title", title)
            putExtra("description", description)
            putExtra("imageUrl", imageUrl)
            putExtra("published", published)
            putExtra("fullArticleUrl", articleUrl)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            System.currentTimeMillis().toInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )



        // Load small image for thumbnail
        val smallImage = runBlocking {
            ImageLoaderUtils.loadBitmapWithCoil(context, imageUrl)
        } ?: BitmapFactory.decodeResource(context.resources, R.drawable.placeholder_image)

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(description)
            .setLargeIcon(smallImage)
            .setStyle(NotificationCompat.BigTextStyle().bigText(description))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val manager = context.getSystemService(NotificationManager::class.java)

        // Create channel for Android 8+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "News Notifications",
                NotificationManager.IMPORTANCE_HIGH
            )
            manager.createNotificationChannel(channel)
        }

        manager.notify((System.currentTimeMillis() % 100000).toInt(), builder.build())
    }
}*/


//package com.example.notificationsproject.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.toBitmap
import com.example.notificationsproject.R
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.notificationsproject.ui.theme.NewsDetailActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object NotificationUtils {

    private const val CHANNEL_ID = "news_small_image_channel"

    // Call this from a coroutine (safe). It does IO on Dispatchers.IO internally.
    suspend fun showSmallImageNotificationSuspend(
        context: Context,
        title: String,
        description: String,
        imageUrl: String?,
        articleUrl: String,
        published: String
    ) {
        // 1) load bitmap on IO dispatcher via Coil
        val bitmap: Bitmap? = withContext(Dispatchers.IO) {
            loadBitmapWithCoil(context, imageUrl)
        } ?: BitmapFactory.decodeResource(context.resources, R.drawable.placeholder_image)

        postNotification(context, title, description, bitmap, articleUrl)
    }

    private fun postNotification(
        context: Context,
        title: String,
        description: String,
        bitmap: Bitmap?,
        articleUrl: String
    ) {
        val channelId = CHANNEL_ID

        val intent = Intent(context, NewsDetailActivity::class.java).apply {
            putExtra("title", title)
            putExtra("description", description)
            putExtra("imageUrl", "") // you can add imageUrl if needed
            putExtra("fullArticleUrl", articleUrl)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            System.currentTimeMillis().toInt(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(description)
            .setLargeIcon(bitmap)
            .setStyle(NotificationCompat.BigTextStyle().bigText(description))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setOngoing(true)

        val manager = context.getSystemService(NotificationManager::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "News Notifications", NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
        }

        val id = System.currentTimeMillis().toInt()
        manager.notify(id, builder.build())
    }

    // Use Coil to download & decode. This is suspend and must be called from coroutine.
    private suspend fun loadBitmapWithCoil(context: Context, url: String?): Bitmap? {
        if (url.isNullOrEmpty()) return null

        return try {
            val loader = ImageLoader.Builder(context)
                .allowHardware(false) // notifications need software bitmaps
                .build()

            val request = ImageRequest.Builder(context)
                .data(url)
                .allowHardware(false)
                .size(500) // request a scaled size (max) to reduce memory / decode cost
                .build()

            val result = loader.execute(request)
            if (result is SuccessResult) {
                // convert drawable to bitmap and optionally resize further
                val bmp = result.drawable.toBitmap()
                // extra safety: scale to thumbnail width if needed
                val maxWidth = 300
                if (bmp.width > maxWidth) {
                    val aspect = bmp.height.toFloat() / bmp.width.toFloat()
                    Bitmap.createScaledBitmap(bmp, maxWidth, (maxWidth * aspect).toInt(), true)
                } else bmp
            } else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
