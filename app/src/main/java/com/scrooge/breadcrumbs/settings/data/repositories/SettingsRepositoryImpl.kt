package com.scrooge.breadcrumbs.settings.data.repositories

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.scrooge.breadcrumbs.databaseapi.data.internationalization.LocalizationDao
import com.scrooge.breadcrumbs.settings.domain.repositories.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val TAG = "SettingsRepositoryImpl"

class SettingsRepositoryImpl @Inject constructor(
    private val localizationDao: LocalizationDao,
    private val dataStore: DataStore<Preferences>,
) : SettingsRepository {
    override val allLanguages: Flow<List<String>> =
        localizationDao.getAllLanguages().map { languages -> languages.map{ it.languageCode } }
    override val selectedLanguage: Flow<String> = dataStore.safeData
        .map { preferences ->
            preferences[LANGUAGE_KEY] ?: DEFAULT_LANGUAGE
        }

    override suspend fun updateSelectedLanguage(language: String) {
        dataStore.edit { preferences ->
            preferences[LANGUAGE_KEY] = language
        }
    }

    private val DataStore<Preferences>.safeData get() = this.data.catchPreferencesIOException()

    private fun Flow<Preferences>.catchPreferencesIOException() = this.catch {
        if (it is IOException) {
            Log.e(TAG, "Error reading preferences", it)
            emit(emptyPreferences())
        } else {
            throw it
        }
    }

    companion object {
        private val LANGUAGE_KEY = stringPreferencesKey("language")
        private const val DEFAULT_LANGUAGE = "en"
    }
}
