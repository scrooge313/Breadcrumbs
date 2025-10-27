package com.scrooge.breadcrumbs.overview.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scrooge.breadcrumbs.baking.model.Baking
import com.scrooge.breadcrumbs.core.CONFIGURATION_CHANGE_TIMEOUT
import com.scrooge.breadcrumbs.baking.data.BakingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.OffsetDateTime
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val bakingsRepository: BakingsRepository,
) : ViewModel() {
    val uiState = bakingsRepository.getAllBakings()
        .map { bakings ->
            OverviewUiState(bakings = bakings.map { baking ->
                Baking(
                    baking.id,
                    OffsetDateTime.now(), baking.name
                )
            })
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(CONFIGURATION_CHANGE_TIMEOUT),
            OverviewUiState(bakings = emptyList())
        )

//    fun deleteBaking(index: Int) {
//        _uiState.value = _uiState.value.copy()
//        _uiState.update {
//            it.copy(bakings = it.bakings.filterIndexed { filterIndex, _ -> filterIndex != index })
//        }
//    }
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