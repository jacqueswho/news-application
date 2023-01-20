package com.news.app.feature.news.data.preferences

import android.content.SharedPreferences
import com.news.app.feature.news.domain.preferences.Preferences
import javax.inject.Inject

class NewsPreferences @Inject constructor(
    private val sharedPref: SharedPreferences
) : Preferences {

    override fun savePreferredCountry(countryCode: String) {
        sharedPref.edit().putString(Preferences.PREFERRED_COUNTRY_KEY, countryCode).apply()
    }

    override fun getPreferredCountry(): String? {
        return sharedPref.getString(Preferences.PREFERRED_COUNTRY_KEY, Preferences.DEFAULT_COUNTRY)
    }
}