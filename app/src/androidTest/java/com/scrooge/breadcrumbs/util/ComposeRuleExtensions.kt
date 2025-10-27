package com.scrooge.breadcrumbs.util

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.scrooge.breadcrumbs.R

fun <A: ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithStringId(
    @StringRes stringId: Int
): SemanticsNodeInteraction = onNodeWithText(activity.getString(stringId))

fun <A: ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onUpButton(): SemanticsNodeInteraction =
    onNodeWithContentDescription(activity.getString(R.string.back_button))