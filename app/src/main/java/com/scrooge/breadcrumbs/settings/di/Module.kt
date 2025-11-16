package com.scrooge.breadcrumbs.settings.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.scrooge.breadcrumbs.settings.data.repositories.SettingsRepositoryImpl
import com.scrooge.breadcrumbs.settings.domain.repositories.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings_preferences")

@Module
@InstallIn(SingletonComponent::class) // todo validate
abstract class SettingsBindings {
    @Binds
    abstract fun bindSettingsRepository(
        settingsRepository: SettingsRepositoryImpl
    ): SettingsRepository
}

@Module
@InstallIn(SingletonComponent::class) // todo validate
object SettingsProvides {
    @Provides
    fun providePreferences(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }
}

