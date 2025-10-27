package com.scrooge.breadcrumbs.util

import androidx.navigation.NavController
import com.scrooge.breadcrumbs.app.NavigationDestination
import org.junit.Assert.assertEquals

inline fun <reified T: NavigationDestination> NavController.assertCurrentRoute() {
    assertEquals(true, this.currentBackStackEntry?.destination?.route?.startsWith(T::class.qualifiedName!!))
}
