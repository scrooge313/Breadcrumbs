package com.scrooge.breadcrumbs.baking.ui

import androidx.lifecycle.ViewModel
import com.scrooge.breadcrumbs.baking.data.DummyBakingDatasource
import com.scrooge.breadcrumbs.baking.model.BakingId
import com.scrooge.breadcrumbs.data.BakingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.collections.filterIndexed

@HiltViewModel
class BakingViewModel @Inject constructor(
    val bakingDatasource: DummyBakingDatasource,
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
        bakingDatasource.getBaking(bakingId)
}
