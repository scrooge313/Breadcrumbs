package com.scrooge.breadcrumbs.data

import com.scrooge.breadcrumbs.baking.data.Baking
import kotlinx.coroutines.flow.Flow

interface BakingsRepository {
    fun getAllBakings(): Flow<List<Baking>>
    fun getAllBakings(id: Int): Flow<Baking>
    suspend fun insertBaking(baking: Baking)
    suspend fun updateBaking(baking: Baking)
    suspend fun deleteBaking(baking: Baking)
}