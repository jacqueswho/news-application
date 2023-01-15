package com.news.app.core.api

import ArticleResponse
import com.news.app.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getNewsArticles(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String =
            BuildConfig.NEWS_API_KEY
    ): Response<ArticleResponse>
}