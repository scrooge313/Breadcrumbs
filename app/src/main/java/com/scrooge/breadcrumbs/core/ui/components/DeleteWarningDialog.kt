package com.scrooge.breadcrumbs.core.ui.components

import androidx.annotation.StringRes
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.scrooge.breadcrumbs.R

@Composable
fun deleteWarningDialog( // todo makes lowercase sense?
    confirmAction: () -> Unit,
    @StringRes text: Int,
    modifier: Modifier = Modifier
): () -> Unit {
    var showDialog by remember { mutableStateOf(false) }
    val dismiss = { showDialog = false }
    if(showDialog) {
        AlertDialog(
            onDismissRequest = dismiss,
            title = { Text(stringResource(R.string.are_you_sure)) },
            text = { Text(stringResource(text)) },
            modifier = modifier,
            dismissButton = {
                TextButton(onClick = dismiss) {
                    Text(stringResource(R.string.cancel))
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    confirmAction()
                    dismiss()
                }) {
                    Text(stringResource(R.string.delete), color = MaterialTheme.colorScheme.error)
                }
            },
        )
    }
    return { showDialog = true }
}
