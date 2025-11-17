package com.scrooge.breadcrumbs.app

import android.content.Context
import android.content.Intent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.scrooge.breadcrumbs.R
import com.scrooge.breadcrumbs.baking.model.BakingId
import com.scrooge.breadcrumbs.baking.ui.BakingScreen
import com.scrooge.breadcrumbs.overview.ui.OverviewScreen
import com.scrooge.breadcrumbs.settings.ui.SettingsScreen
import kotlinx.serialization.Serializable

private sealed interface NavigationDestination {
    val isSubscreen: Boolean

    @Serializable
    object Overview : NavigationDestination {
        override val isSubscreen = false
    }

    @Serializable
    data class Baking(val bakingId: BakingId) : NavigationDestination {
        override val isSubscreen = false
    }

    @Serializable
    object Settings : NavigationDestination {
        override val isSubscreen = true
    }
}

private fun NavBackStackEntry?.toRoute(): NavigationDestination? {
    return when (this?.destination?.route?.split("/")?.first()) {
        null -> null
        NavigationDestination.Overview::class.qualifiedName -> this.toRoute<NavigationDestination.Overview>()
        NavigationDestination.Baking::class.qualifiedName -> this.toRoute<NavigationDestination.Baking>()
        NavigationDestination.Settings::class.qualifiedName -> this.toRoute<NavigationDestination.Settings>()
        else -> error("Unspecified route")
    }
}

fun NavController.navigateBackHome() {
    this.popBackStack(
        route = NavigationDestination.Overview,
        inclusive = false,
    )
}

fun shareRecipe(context: Context) {
    // todo proper implementation
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, "Test.")
        putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
    }
    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.share)
        )
    )
}

@Composable
fun BreadcrumbsApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    // necessary for updates on route change
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.toRoute()
    Scaffold(
        topBar = {
            if (currentRoute?.isSubscreen == true) {
                TopUpBar({ navController.navigateUp() })
            } else {
                TopBar(
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() },
                    navigateToSettings = { navController.navigate(NavigationDestination.Settings) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        modifier = modifier
    ) { innerPadding ->
        val navHostFadeDuration = 200
        NavHost(
            navController = navController,
            startDestination = NavigationDestination.Overview,
            enterTransition = { fadeIn(animationSpec = tween(navHostFadeDuration)) },
            exitTransition = { fadeOut(animationSpec = tween(navHostFadeDuration)) },
            modifier = modifier.padding(innerPadding)
        ) {
            composable<NavigationDestination.Overview> {
                OverviewScreen(
                    onSelectBaking = { bakingId ->
                        navController.navigate(NavigationDestination.Baking(bakingId))
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable<NavigationDestination.Baking> {
                val route = it.toRoute<NavigationDestination.Baking>()
                val context = LocalContext.current
                BakingScreen(
                    bakingId = route.bakingId,
                    onCancel = {
                        navController.navigateBackHome()
                    },
                    onShare = {
                        shareRecipe(context)
                    },
                    modifier = Modifier.fillMaxSize(),
                )
            }
            composable<NavigationDestination.Settings> {
                SettingsScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    navigateToSettings: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        },
        actions = {
            IconButton(
                onClick = navigateToSettings,
            ) {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = stringResource(R.string.settings),
                    modifier = Modifier.fillMaxSize()
                )
            }
        },
        title = {
            Row(
                modifier = Modifier.height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val image = painterResource(R.drawable.raw_bread)
                Image(
                    painter = image,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxHeight(0.7f)
                        .aspectRatio(1f)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopUpBar(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back_button)
                )
            }
        },
        title = { },
        modifier = modifier
    )
}