package com.news.app.feature.news.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.news.app.core.api.NewsApi
import com.news.app.feature.news.data.NewsPagingSource
import com.news.app.feature.news.domain.repository.NewsRepository
import com.news.app.feature.news.preferences.NewsSettings
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val newsSettings: NewsSettings
) : NewsRepository {

    override fun getPreferredCountry() = newsSettings.getPreferredCountry()

    override fun getPagedBreakingNewsArticlesByCountry(country: String) =
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