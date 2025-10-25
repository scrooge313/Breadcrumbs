package com.scrooge.breadcrumbs.overview.ui

import androidx.lifecycle.ViewModel
import com.scrooge.breadcrumbs.baking.data.DummyBakingDatasource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.collections.filterIndexed

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val bakingDatasource: DummyBakingDatasource,
) : ViewModel() {
    private val _uiState = MutableStateFlow<OverviewUiState>(OverviewUiState(
        bakings = bakingDatasource.getBakings()
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