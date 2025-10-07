package com.scrooge.breadcrumbs.overview.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.scrooge.breadcrumbs.core.ui.theme.BreadcrumbsTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.time.OffsetDateTime

class OverviewTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun computeDeltaTime_0() {
        composeTestRule.setContent {
            val deltaTime = computeTimeDelta(OffsetDateTime.now())
            assertEquals("0 days ago", deltaTime)
        }
    }

    @Test
    fun computeDeltaTime_1() {
        composeTestRule.setContent {
            val deltaTime = computeTimeDelta(OffsetDateTime.now().minusDays(1))
            assertEquals("1 day ago", deltaTime)
        }
    }

    @Test
    fun delete_entry() {
        composeTestRule.setContent {
            BreadcrumbsTheme {
                Overview(Modifier.fillMaxSize())
            }
        }
        composeTestRule.onNodeWithText("Brioche").assertExists()
        composeTestRule.onNodeWithText("Brioche").performClick()
        composeTestRule.onNodeWithText("Delete").assertExists()
        composeTestRule.onNodeWithText("Delete").performClick()
        composeTestRule.onNodeWithText("Brioche").assertDoesNotExist()
    }
}
