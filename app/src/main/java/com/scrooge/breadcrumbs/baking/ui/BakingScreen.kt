package com.scrooge.breadcrumbs.baking.ui

import androidx.annotation.VisibleForTesting
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.scrooge.breadcrumbs.R
import com.scrooge.breadcrumbs.baking.model.BakingId
import com.scrooge.breadcrumbs.core.ui.theme.BreadcrumbsTheme
import java.time.OffsetDateTime

@VisibleForTesting
@Composable
internal fun computeTimeDelta(dateTime: OffsetDateTime): String {
    val deltaTimeInSeconds = OffsetDateTime.now().toEpochSecond() - dateTime.toEpochSecond()
    val deltaTimeInDays = (deltaTimeInSeconds / 60 / 60 / 24).toInt()
    return pluralStringResource(R.plurals.days_ago, deltaTimeInDays, deltaTimeInDays)
}

@Composable
fun BakingScreen(
    bakingId: BakingId,
    onCancel: () -> Unit,
    onShare: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BakingViewModel = hiltViewModel<BakingViewModel>()
) {
    Column(modifier = modifier.padding(dimensionResource(R.dimen.medium))) {
        Text(bakingId.toString())
        Button(
            onClick = onShare,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(stringResource(R.string.share))
        }
        OutlinedButton(
            onClick = onCancel,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(stringResource(R.string.cancel))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BakingPreview() {
    BreadcrumbsTheme {
        BakingScreen(
            modifier = Modifier.fillMaxSize(),
            bakingId = 1,
            onCancel = { },
            onShare = { }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DarkBakingPreview() {
    BreadcrumbsTheme(darkTheme = true) {
        BakingScreen(
            modifier = Modifier.fillMaxSize(),
            bakingId = 1,
            onCancel = { },
            onShare = { }
        )
    }
}
