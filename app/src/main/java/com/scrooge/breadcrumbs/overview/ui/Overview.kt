package com.scrooge.breadcrumbs.overview.ui

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
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scrooge.breadcrumbs.R
import com.scrooge.breadcrumbs.core.ui.theme.BreadcrumbsTheme
import com.scrooge.breadcrumbs.overview.data.DummyBakingDatasource
import com.scrooge.breadcrumbs.overview.model.Baking
import java.time.LocalDate
import java.time.OffsetDateTime

@VisibleForTesting
@Composable
internal fun computeTimeDelta(dateTime: OffsetDateTime): String {
    val deltaTimeInSeconds = OffsetDateTime.now().toEpochSecond() - dateTime.toEpochSecond()
    val deltaTimeInDays = (deltaTimeInSeconds / 60 / 60 / 24).toInt()
    return pluralStringResource(R.plurals.days_ago, deltaTimeInDays, deltaTimeInDays)
}

@Composable
fun Overview(modifier: Modifier = Modifier) {
    LocalDate.now()
    OffsetDateTime.now()
    Scaffold(
        topBar = {
            TopBar()
        },
        modifier = modifier
    ) { innerPadding ->
        var bakings by remember { mutableStateOf(DummyBakingDatasource().getBakings()) }
        LazyColumn(
            contentPadding = innerPadding,
            modifier = Modifier
//                    .verticalScroll(rememberScrollState()),
        ) {
            itemsIndexed(bakings) { index, it ->
                BakingEntry(
                    it, Modifier
                        .clickable {
                            bakings =
                                bakings.filterIndexed { filterIndex, _ -> filterIndex != index }
                        }
                        .padding(dimensionResource(R.dimen.tiny))
                        .fillMaxWidth()
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                modifier = Modifier.height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val image = painterResource(R.drawable.bread)
                Image(
                    painter = image,
                    contentDescription = null,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .fillMaxHeight()
                )
                Spacer(Modifier.width(dimensionResource(R.dimen.small)))
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge,
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun BakingEntry(item: Baking, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState( // todo just used for demo purposes
        if (expanded) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.primaryContainer
    )
    Card(
        modifier = modifier,
        border = BorderStroke(
            dimensionResource(R.dimen.barely),
            MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.medium))
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                ),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.type,
                )
                Spacer(
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = computeTimeDelta(item.date),
                    fontSize = 15.sp
                )
                BakingEntryToggle(expanded = expanded, onClick = {
                    expanded = !expanded
                })
            }
            if (expanded) {
                BakingDetails()
            }
        }
    }
}

@Composable
private fun BakingEntryToggle(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.expand_button_content_description),
        )
    }
}

@Composable
private fun BakingDetails(modifier: Modifier = Modifier) {
    Text("Placeholder")
}

@Preview(showBackground = true)
@Composable
fun OverviewPreview() {
    BreadcrumbsTheme {
        Overview(Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true)
@Composable
fun DarkOverviewPreview() {
    BreadcrumbsTheme(darkTheme = true) {
        Overview(Modifier.fillMaxSize())
    }
}
