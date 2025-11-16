package com.example.notificationsproject


import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.notificationsproject.dao.NewsRepository

class NewsSyncWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val repo = NewsRepository()
        repo.fetchAndPushNews()
        return Result.success()
    }
}
