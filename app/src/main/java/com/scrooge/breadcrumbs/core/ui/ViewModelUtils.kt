package com.scrooge.breadcrumbs.core.ui

import com.scrooge.breadcrumbs.core.CONFIGURATION_CHANGE_TIMEOUT
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

object ViewModelUtils {
    fun <T> Flow<T>.stateIn(scope: CoroutineScope, defaultValue: T) =
        this.stateIn(scope, SharingStarted.WhileSubscribed(CONFIGURATION_CHANGE_TIMEOUT), defaultValue)
}
