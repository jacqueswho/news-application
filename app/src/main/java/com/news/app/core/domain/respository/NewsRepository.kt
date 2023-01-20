package com.news.app.core.domain.respository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.news.app.core.data.remote.news.model.Article

interface NewsRepository {

    fun getPreferredCountry(): String?

    fun setPreferredCountry(countryCode: String)

    fun getPagedBreakingNewsArticlesByCountry(country: String): LiveData<PagingData<Article>>
}