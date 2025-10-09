package com.scrooge.breadcrumbs.baking.ui

import androidx.lifecycle.ViewModel
import com.scrooge.breadcrumbs.baking.data.DummyBakingDatasource
import com.scrooge.breadcrumbs.baking.model.BakingId
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.collections.filterIndexed

class BakingViewModel : ViewModel() {
//    private val _uiState = MutableStateFlow<BakingUiState>(BakingUiState(
//        baking = DummyBakingDatasource().getBaking(1)
//    ))
//    val uiState = _uiState.asStateFlow()

    fun deleteBaking() {
        TODO()
    }

    fun getBaking(bakingId: BakingId) =
        DummyBakingDatasource().getBaking(bakingId)
}
