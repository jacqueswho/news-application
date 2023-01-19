package com.news.app.feature.news.preferences

import android.content.Context
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsSettings @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        private const val DEFAULT_COUNTRY = "us"
        private const val PREFERENCES_KEY = "PREFERRED_COUNTRY"
    }

    fun getPreferredCountry(): String? {
        val sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(context)
        val country = sharedPreferences.getString(PREFERENCES_KEY, DEFAULT_COUNTRY)
        return country
    }
}