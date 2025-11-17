package com.scrooge.breadcrumbs.baking.ui

import androidx.lifecycle.ViewModel
import com.scrooge.breadcrumbs.baking.domain.repositories.BakingsRepository
import com.scrooge.breadcrumbs.baking.model.BakingId
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BakingViewModel @Inject constructor(
    val bakingsRepository: BakingsRepository
) : ViewModel() {
//    private val _uiState = MutableStateFlow<BakingUiState>(BakingUiState(
//        baking = DummyBakingDatasource().getBaking(1)
//    ))
//    val uiState = _uiState.asStateFlow()

    fun deleteBaking() {
        TODO()
    }

    fun getBaking(bakingId: BakingId) =
        bakingsRepository.getBakingDetails(bakingId)
}
