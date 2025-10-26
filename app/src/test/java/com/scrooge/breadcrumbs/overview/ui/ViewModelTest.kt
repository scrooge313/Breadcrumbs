package com.scrooge.breadcrumbs.overview.ui

import com.scrooge.breadcrumbs.baking.data.Baking
import com.scrooge.breadcrumbs.data.BakingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class ViewModelTest {
    val mockRepository: BakingsRepository = object : BakingsRepository {
        override fun getAllBakings(): Flow<List<Baking>> {
            return flow {
                emit(listOf(Baking(1, "Sourdough"), Baking(2, "Brioche")))
            }
        }

        override fun getBaking(id: Int): Flow<Baking> {
            TODO("Not yet implemented")
        }

        override suspend fun insertBaking(baking: Baking) {
            TODO("Not yet implemented")
        }

        override suspend fun updateBaking(baking: Baking) {
            TODO("Not yet implemented")
        }

        override suspend fun deleteBaking(baking: Baking) {
            TODO("Not yet implemented")
        }

    }

    val viewModel = OverviewViewModel(mockRepository)

    @Test
    fun viewModel_deletedBaking_bakingDeleted() = runTest {
        var uiStates = mutableListOf<OverviewUiState>()
        viewModel.uiState.toList(uiStates)
//        viewModel.deleteBaking(0)
        Assert.assertTrue(uiStates.last().bakings.isNotEmpty())
//        Assert.assertEquals(
//            originalBakings.filterIndexed { index, baking -> index > 0 },
//            viewModel.uiState.value.bakings
//        )
    }
}
