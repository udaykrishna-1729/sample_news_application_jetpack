package com.example.notificationsproject


import com.example.notificationsproject.dao.NewsArticle
import retrofit2.http.GET
import retrofit2.http.Query

data class NewsResponse(
    val status: String? = null,
    val totalResults: Int? = null,
    val results: List<NewsArticle>? = null
)
interface NewsApi {

    @GET("latest")
    suspend fun getTopHeadlines(
        @Query("apikey") apiKey: String,
        @Query("country") country: String = "in",
        @Query("language") language: String = "en"
    ): NewsResponse
}
