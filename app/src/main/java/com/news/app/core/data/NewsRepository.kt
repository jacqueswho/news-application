package com.news.app.core.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.news.app.core.api.NewsApi
import com.news.app.core.settings.NewsSettings
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val newsApi: NewsApi,
    private val newsSettings: NewsSettings
) {

    fun getPreferredCountry() = newsSettings.getPreferredCountry()

    fun getBreakingNewsArticlesByCountry(country: String) =
        Pager(
            config =
            PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { NewsPagingSource(newsApi, country) }
        ).liveData
}