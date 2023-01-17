package com.news.app.core.presentation

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.news.app.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}