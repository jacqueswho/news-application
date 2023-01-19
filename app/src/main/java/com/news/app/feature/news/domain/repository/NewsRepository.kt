package com.news.app.feature.news.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.news.app.feature.news.model.Article

interface NewsRepository {

    fun getPreferredCountry(): String?

    fun getPagedBreakingNewsArticlesByCountry(country: String): LiveData<PagingData<Article>>
}