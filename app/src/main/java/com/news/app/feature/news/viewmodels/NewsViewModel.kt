package com.news.app.feature.news.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.news.app.core.data.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    sealed class Country(val countryCode: String) {
        object USA : Country("us")
        object Canada : Country("ca")
    }

    companion object {
        private const val DEFAULT_COUNTRY = "us"
        private const val CURRENT_COUNTRY = "NEWS_CURRENT_COUNTRY"
    }

    private var defaultCountry = newsRepository.getPreferredCountry() ?: DEFAULT_COUNTRY

    private val currentCountry = savedStateHandle.getLiveData(CURRENT_COUNTRY, defaultCountry)
    val articles = currentCountry.switchMap { country ->
        newsRepository.getBreakingNewsArticlesByCountry(country).cachedIn(viewModelScope)
    }

    fun searchNewsByCountry(country: Country) {
        currentCountry.value = country.countryCode
    }

    fun searchNewsByDefaultCountry() {
        currentCountry.value = defaultCountry
    }
}