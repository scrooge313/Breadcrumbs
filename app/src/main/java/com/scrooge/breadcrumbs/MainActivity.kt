package com.scrooge.breadcrumbs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import com.scrooge.breadcrumbs.core.ui.theme.BreadcrumbsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BreadcrumbsTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val layoutDirection = LocalLayoutDirection.current
                    BreadcrumbsApp(
                        modifier = Modifier
                            .fillMaxSize()
                            .statusBarsPadding() // for not overlapping the status bar
                            .padding( // for not overlapping the status bar
                                start = WindowInsets.safeDrawing.asPaddingValues()
                                    .calculateStartPadding(layoutDirection),
                                end = WindowInsets.safeDrawing.asPaddingValues()
                                    .calculateEndPadding(layoutDirection)
                            )
                    )
                }
            }
        }
    }
}
