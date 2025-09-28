package com.scrooge.breadcrumbs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.scrooge.breadcrumbs.overview.ui.Overview
import com.scrooge.breadcrumbs.core.theme.BreadcrumbsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BreadcrumbsTheme {
                Overview(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
