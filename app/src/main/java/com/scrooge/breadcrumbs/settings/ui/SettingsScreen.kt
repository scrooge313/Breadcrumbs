package com.scrooge.breadcrumbs.settings.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.EmojiSupportMatch
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import java.util.Locale

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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text("Sprache")
                    Dropdown(
                        items = allLanguages,
                        selectedItem = selectedLanguage,
                        onItemSelected = settingsViewModel::setSelectedLanguages,
                        labelProvider = { it },
                    )
                }
                Text("NEXT 1")
                Text("NEXT")
                Text("NEXT")
                Text("NEXT")
                Text("NEXT")
                Text("NEXT")
                Text("NEXT")
                Text("NEXT")
            }
        }
    }
}

@Composable
fun <T> Dropdown(
    items: Collection<T>,
    selectedItem: T?,
    onItemSelected: (T) -> Unit,
    labelProvider: (T) -> String,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    minWidth: Dp = 200.dp,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Box(
        modifier = modifier
    ) {
        Card(
            shape = MaterialTheme.shapes.extraSmall,
            modifier = Modifier
                .widthIn(min = minWidth)
                .clickable { expanded = true }
        ) {
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(horizontal = 5.dp),
            ) {
                Text(
                    text = selectedItem?.let { labelProvider(selectedItem) } ?: placeholder!!,
                )
                Icon(
                    imageVector = if (expanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                    contentDescription = null,
                )
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .widthIn(min = minWidth)
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = labelProvider(item),
                            textAlign = TextAlign.End,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    onClick = {
                        expanded = false
                        onItemSelected(item)
                    },
                )
            }
        }
    }
}
