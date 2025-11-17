package com.scrooge.breadcrumbs.settings.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scrooge.breadcrumbs.core.ui.ViewModelUtils.stateIn
import com.scrooge.breadcrumbs.settings.domain.repositories.SettingsRepository
import com.scrooge.breadcrumbs.settings.domain.state.Language
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    val allLanguages: StateFlow<List<Language>> = settingsRepository.allLanguages
        .stateIn(
            viewModelScope,
            emptyList(),
        )
    val selectedLanguage: StateFlow<Language?> = settingsRepository.selectedLanguage
        .stateIn(
            viewModelScope,
            null,
        )
    fun setSelectedLanguages(language: String) = viewModelScope.launch {
        settingsRepository.updateSelectedLanguage(language)
    }
}
