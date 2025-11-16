package com.scrooge.breadcrumbs.settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    settingsViewModel: SettingsViewModel = hiltViewModel(),
) {
    Column(
        modifier = modifier,
    ) {
        val allLanguages by settingsViewModel.allLanguages.collectAsStateWithLifecycle()
        val selectedLanguage by settingsViewModel.selectedLanguage.collectAsStateWithLifecycle()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            var expanded by rememberSaveable { mutableStateOf(false) }
            Text("Sprache")
            Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
                Text(
                    text = selectedLanguage,
//                onValueChange = {},
                    modifier = Modifier.clickable { expanded = true },
//                readOnly = false,
//                trailingIcon = {
//                    Icon(
//                        imageVector = if(expanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
//                        contentDescription = null,
//                    )
//                },
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
//                modifier = TODO(),
//                offset = TODO(),
//                scrollState = TODO(),
//                properties = TODO(),
//                shape = TODO(),
//                containerColor = TODO(),
//                tonalElevation = TODO(),
//                shadowElevation = TODO(),
//                border = TODO()
                ) {
                    allLanguages.forEach {
                        DropdownMenuItem(
                            text = { Text(it) },
                            onClick = {
                                settingsViewModel.setSelectedLanguages(it)
                                expanded = false
                            },
                        )
                    }
                }
            }
        }
    }
}