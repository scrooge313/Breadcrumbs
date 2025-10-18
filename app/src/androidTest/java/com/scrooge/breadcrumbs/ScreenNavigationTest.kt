package com.scrooge.breadcrumbs

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.scrooge.breadcrumbs.util.assertCurrentRoute
import com.scrooge.breadcrumbs.util.onNodeWithStringId
import com.scrooge.breadcrumbs.util.onUpButton

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ScreenNavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            BreadcrumbsApp(navController = navController)
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        assertEquals(true, navController.currentBackStackEntry?.destination?.route?.startsWith(NavigationDestination.Overview::class.qualifiedName!!))
        navController.assertCurrentRoute<NavigationDestination.Overview>()
        composeTestRule.onNodeWithStringId(R.string.app_name).assertExists()
        composeTestRule.onUpButton().assertDoesNotExist()
    }

    @Test
    fun navHost_clickBaking_navigatesToBakingScreen() {
        composeTestRule.onNodeWithText("Brioche").performClick()
        navController.assertCurrentRoute<NavigationDestination.Baking>()
        composeTestRule.onUpButton().assertExists()
        composeTestRule.onUpButton().performClick()
        navController.assertCurrentRoute<NavigationDestination.Overview>()
    }
}
