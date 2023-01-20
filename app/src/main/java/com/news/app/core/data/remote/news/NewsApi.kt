package com.news.app.core.data.remote.news

import com.news.app.BuildConfig
import com.news.app.core.data.remote.news.model.ArticleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun topNewsForCountry(
        @Query("country") country: String,
        @Query("page") pageNumber: Int = 1,
        @Query("pageSize") pageSize: Int = 10,
        @Query("apiKey") apiKey: String =
            BuildConfig.NEWS_API_KEY
    ): Response<ArticleResponse>

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
    }
}