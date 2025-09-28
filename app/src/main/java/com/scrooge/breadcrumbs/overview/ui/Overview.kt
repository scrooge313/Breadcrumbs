package com.scrooge.breadcrumbs.overview.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.scrooge.breadcrumbs.R
import com.scrooge.breadcrumbs.core.theme.BreadcrumbsTheme
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

data class Bake(
    val date: OffsetDateTime,
    val type: String
)

val bakesTemplate = listOf(
    Bake(OffsetDateTime.now(), "Brioche"),
    Bake(OffsetDateTime.now().minusDays(1), "Sourdough"),
    Bake(OffsetDateTime.now().minusDays(2), "Sourdough"),
)

@Composable
fun Overview(modifier: Modifier = Modifier) {
    LocalDate.now()
    OffsetDateTime.now()
    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TitleBar(Modifier.fillMaxWidth())
            var bakes by remember { mutableStateOf(bakesTemplate) }
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                bakes.forEachIndexed { index, it ->
                    BakeEntry(it, Modifier.clickable {
                        bakes = bakes.filterIndexed { filterIndex, _ -> filterIndex != index }
                    }.padding(5.dp))
                }
            }
        }
    }
}

@Composable
fun TitleBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val image = painterResource(R.drawable.bread)
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .aspectRatio(1f)
                .fillMaxHeight()
        )
        Spacer(Modifier.width(5.dp))
        Text(
            text = stringResource(R.string.app_name),
            fontSize = 40.sp,
            lineHeight = 40.sp,
        )
    }
}

@Composable
fun BakeEntry(item: Bake, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .border(1.dp, Color.Black)
    ) {
        Row(
            modifier = Modifier.padding(4.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.type,
            )
            val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            Text(
                text = item.date.format(dateFormatter),
                fontSize = 15.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OverviewPreview() {
    BreadcrumbsTheme {
        Overview(Modifier.fillMaxSize())
    }
}