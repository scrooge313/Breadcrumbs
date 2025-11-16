package com.scrooge.breadcrumbs.settings.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scrooge.breadcrumbs.core.ui.ViewModelUtils.stateIn
import com.scrooge.breadcrumbs.settings.domain.repositories.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    val allLanguages = settingsRepository.allLanguages
        .stateIn(
            viewModelScope,
            emptyList(),
        )
    val selectedLanguage = settingsRepository.selectedLanguage
        .stateIn(
            viewModelScope,
            "",
        )
    fun setSelectedLanguages(language: String) = viewModelScope.launch {
        settingsRepository.updateSelectedLanguage(language)
    }
}
