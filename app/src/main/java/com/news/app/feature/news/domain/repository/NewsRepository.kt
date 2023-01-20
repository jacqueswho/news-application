package com.news.app.feature.news.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.news.app.feature.news.data.remote.model.Article

interface NewsRepository {

    fun getPreferredCountry(): String?

    fun setPreferredCountry(countryCode: String)

    fun getPagedBreakingNewsArticlesByCountry(country: String): LiveData<PagingData<Article>>
}