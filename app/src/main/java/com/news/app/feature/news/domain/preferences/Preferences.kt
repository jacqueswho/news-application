package com.news.app.feature.news.domain.preferences

interface Preferences {

    fun getPreferredCountry(): String?

    fun savePreferredCountry(countryCode: String)

    companion object {
        const val DEFAULT_COUNTRY = "us"
        const val PREFERRED_COUNTRY_KEY = "PREFERRED_COUNTRY"
    }
}