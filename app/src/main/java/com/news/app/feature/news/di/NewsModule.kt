package com.news.app.feature.news.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.news.app.feature.news.data.preferences.NewsPreferences
import com.news.app.feature.news.domain.preferences.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        app: Application
    ): SharedPreferences {
        return app.getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferences(sharedPreferences: SharedPreferences): Preferences {
        return NewsPreferences(sharedPreferences)
    }
}