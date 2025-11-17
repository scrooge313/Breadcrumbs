package com.scrooge.breadcrumbs.settings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.scrooge.breadcrumbs.R
import com.scrooge.breadcrumbs.core.ui.components.SelectDialogTrigger

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    settingsViewModel: SettingsViewModel = hiltViewModel(),
) {
    Box(
        modifier = modifier
    ) {
        val allLanguages by settingsViewModel.allLanguages.collectAsStateWithLifecycle()
        val selectedLanguage by settingsViewModel.selectedLanguage.collectAsStateWithLifecycle()

        Card(
            colors = CardDefaults.cardColors().copy(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            border = CardDefaults.outlinedCardBorder(),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                SelectSetting(
                    settingLabel = stringResource(R.string.language),
                    items = allLanguages,
                    selectedItem = selectedLanguage,
                    onItemSelected = { settingsViewModel.setSelectedLanguages(it.languageCode) },
                    labelProvider = { it.label },
                )
            }
        }
    }
}

@Composable
fun <T> SelectSetting(
    settingLabel: String,
    items: Collection<T>,
    selectedItem: T?,
    onItemSelected: (T) -> Unit,
    labelProvider: (T) -> String,
    modifier: Modifier = Modifier,
) {
    Setting(settingLabel, modifier) {
        SelectDialogTrigger(
            items = items,
            selectedItem = selectedItem,
            onItemSelected = onItemSelected,
            labelProvider = labelProvider,
            placeholder = stringResource(R.string.select),
            modifier = Modifier
                .height(30.dp)
                .widthIn(min = 200.dp)
        )
    }
}

@Composable
fun Setting(
    settingLabel: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(settingLabel)
        content()
    }
}