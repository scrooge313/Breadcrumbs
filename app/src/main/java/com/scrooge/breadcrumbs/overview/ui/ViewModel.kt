package com.scrooge.breadcrumbs.overview.ui

import androidx.lifecycle.ViewModel
import com.scrooge.breadcrumbs.overview.data.DummyBakingDatasource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.collections.filterIndexed

class OverviewViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<OverviewUiState>(OverviewUiState(
        bakings = DummyBakingDatasource().getBakings()
    ))
    val uiState = _uiState.asStateFlow()

    fun deleteBaking(index: Int) {
        _uiState.value = _uiState.value.copy()
        _uiState.update {
            it.copy(bakings = it.bakings.filterIndexed { filterIndex, _ -> filterIndex != index })
        }
    }
}

// todo integrate
//private val viewModel: OverviewViewModel by viewModels()
//
//lifecycleScope.launch {
//    repeatonOnLifecycle(Lifecycle.State.STARTED) {
//        viewModel.uiState.collect { ?
//
//        }
//    }
//}