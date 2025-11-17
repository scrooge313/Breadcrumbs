package com.scrooge.breadcrumbs.settings.domain.repositories

import com.scrooge.breadcrumbs.settings.domain.state.Language
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val allLanguages: Flow<List<Language>>
    val selectedLanguage: Flow<Language>
    suspend fun updateSelectedLanguage(languageCode: String)
}
