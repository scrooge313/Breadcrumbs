package com.scrooge.breadcrumbs.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.scrooge.breadcrumbs.R
import com.scrooge.breadcrumbs.core.UI_FEEDBACK_DELAY_IN_MS
import kotlinx.coroutines.delay
import kotlinx.coroutines.selects.select

@Composable
fun <T> SelectDialog(
    items: Collection<T>,
    selectedItem: T?,
    labelProvider: (T) -> String,
    onItemSelected: (T) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val onConfirm: suspend (T) -> Unit = { item: T ->
        delay(UI_FEEDBACK_DELAY_IN_MS)
        onDismiss()
        onItemSelected(item)
    }
    var newItem by rememberSaveable { mutableStateOf<T?>(null) }
    LaunchedEffect(newItem) {
        if(newItem != null) {
            onConfirm(newItem!!)
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = onDismiss,
            ) { Text(stringResource(R.string.cancel)) }
        },
        title = { Text("TODO") },
        text = {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(items.toList()) { item ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 50.dp)
                            .clickable {
                                newItem = item
                            }

                    ) {
                        RadioButton(
                            selected = item == newItem || (newItem == null && item == selectedItem),
                            onClick = {
                                newItem = item
                            },
                        )
                        Spacer(Modifier.width(10.dp))
                        Text(
                            text = labelProvider(item),
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            }
        },
        modifier = modifier,
    )
}

@Composable
fun <T> SelectDialogTrigger(
    items: Collection<T>,
    selectedItem: T?,
    onItemSelected: (T) -> Unit,
    labelProvider: (T) -> String,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    if (expanded) {
        SelectDialog(
            items = items,
            selectedItem = selectedItem,
            labelProvider = labelProvider,
            onItemSelected = onItemSelected,
            onDismiss = { expanded = false },
        )
    }
    Card(
        shape = MaterialTheme.shapes.extraSmall,
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.outline),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        modifier = modifier
            .clickable { expanded = true }
    ) {
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxHeight()
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
}
