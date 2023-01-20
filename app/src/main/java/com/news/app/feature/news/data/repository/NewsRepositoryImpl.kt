package com.news.app.feature.news.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.news.app.feature.news.data.NewsPagingSource
import com.news.app.feature.news.data.preferences.NewsPreferences
import com.news.app.feature.news.data.remote.NewsApi
import com.news.app.feature.news.domain.repository.NewsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val newsPreferences: NewsPreferences
) : NewsRepository {

    override fun getPreferredCountry() = newsPreferences.getPreferredCountry()

    override fun setPreferredCountry(countryCode: String) {
        newsPreferences.savePreferredCountry(countryCode)
    }

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