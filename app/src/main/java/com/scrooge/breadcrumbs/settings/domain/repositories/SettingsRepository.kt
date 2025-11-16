package com.scrooge.breadcrumbs.settings.domain.repositories

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val allLanguages: Flow<List<String>>
    val selectedLanguage: Flow<String>
    suspend fun updateSelectedLanguage(language: String)
}
