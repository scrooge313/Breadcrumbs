package com.scrooge.breadcrumbs.settings.data.repositories

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.scrooge.breadcrumbs.databaseapi.data.internationalization.LocalizationDao
import com.scrooge.breadcrumbs.databaseapi.data.internationalization.LocalizedLanguageDbState
import com.scrooge.breadcrumbs.settings.domain.repositories.SettingsRepository
import com.scrooge.breadcrumbs.settings.domain.state.Language
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val TAG = "SettingsRepositoryImpl"

@OptIn(ExperimentalCoroutinesApi::class)
class SettingsRepositoryImpl @Inject constructor(
    private val localizationDao: LocalizationDao,
    private val dataStore: DataStore<Preferences>,
) : SettingsRepository {

    private val selectedLanguageCode = dataStore.safeData
        .map { preferences ->
            preferences[LANGUAGE_KEY] ?: DEFAULT_LANGUAGE
        }

    override val selectedLanguage: Flow<Language> = selectedLanguageCode
        .flatMapMerge { languageCode ->
            localizationDao.getLocalizedLanguage(languageCode)
                .map(LocalizedLanguageDbState::toDomainModel)
        }

    override val allLanguages: Flow<List<Language>> = selectedLanguage.flatMapMerge {
        localizationDao.getAllLocalizedLanguages(it.id)
            .map { languages ->
                languages.map(LocalizedLanguageDbState::toDomainModel)
            }
    }

    override suspend fun updateSelectedLanguage(languageCode: String) {
        dataStore.edit { preferences ->
            preferences[LANGUAGE_KEY] = languageCode
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

private fun LocalizedLanguageDbState.toDomainModel() = Language(
    id = this.language.id,
    languageCode = this.language.languageCode,
    label = this.label,
)
