package com.news.app.core.settings

import android.content.Context
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsSettings @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getPreferredCountry(): String? {
        val sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(context)
        val country = sharedPreferences.getString("news_country", "us")
        return country
    }
}