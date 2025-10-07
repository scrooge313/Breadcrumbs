package com.scrooge.breadcrumbs.overview.ui

import org.junit.Assert
import org.junit.Test

class ViewModelTest {
    val viewModel = OverviewViewModel()

    @Test
    fun viewModel_deletedBaking_bakingDeleted() {
        val originalBakings = viewModel.uiState.value.bakings
        viewModel.deleteBaking(0)
        Assert.assertTrue(originalBakings.isNotEmpty())
        Assert.assertEquals(
            originalBakings.filterIndexed { index, baking -> index > 0 },
            viewModel.uiState.value.bakings
        )
    }
}
