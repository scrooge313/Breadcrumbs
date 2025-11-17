package com.scrooge.breadcrumbs.baking.domain.repositories

import com.scrooge.breadcrumbs.baking.domain.state.BakingDetails
import com.scrooge.breadcrumbs.baking.domain.state.BakingMetaData
import kotlinx.coroutines.flow.Flow

interface BakingsRepository {
    fun getAllBakings(): Flow<List<BakingMetaData>>
    fun getBakingDetails(bakingId: Long): Flow<BakingDetails>
}
