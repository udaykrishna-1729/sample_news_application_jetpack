package com.example.notificationsproject


import android.content.Context
import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult


object ImageLoaderUtils {

    // Loads an image as Bitmap (works for all image URLs)
    suspend fun loadBitmapWithCoil(context: Context, url: String?): Bitmap? {
        if (url.isNullOrEmpty()) return null

        return try {
            val loader = ImageLoader(context)

            val request = ImageRequest.Builder(context)
                .data(url)
                .allowHardware(false)   // Notifications cannot use hardware bitmaps
                .build()

            val result = loader.execute(request)

            if (result is SuccessResult) {
                result.drawable.toBitmap()
            } else null

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
