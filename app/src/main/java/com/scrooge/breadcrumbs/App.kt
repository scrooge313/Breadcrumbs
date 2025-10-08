package com.scrooge.breadcrumbs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.scrooge.breadcrumbs.baking.model.BakingId
import com.scrooge.breadcrumbs.overview.ui.OverviewScreen
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

sealed interface NavigationDestination {
    @Serializable
    object Overview : NavigationDestination
    @Serializable
    data class BakingDetails(val bakingId: BakingId) : NavigationDestination
}

@Composable
fun BreadcrumbsApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        topBar = {
            TopBar()
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavigationDestination.Overview,
            modifier = modifier.padding(innerPadding)
        ) {
            composable<NavigationDestination.Overview> {
                navController.navigate(NavigationDestination.BakingDetails(1))
                OverviewScreen(
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable<NavigationDestination.BakingDetails> {
                Column {
                    Text("HELLO")
                    Text(it.destination.route.toString())
                    Text(it.toRoute<NavigationDestination.BakingDetails>().bakingId.toString())
                }
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