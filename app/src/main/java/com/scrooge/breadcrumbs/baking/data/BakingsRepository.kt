package com.scrooge.breadcrumbs.baking.data

import com.scrooge.breadcrumbs.databaseapi.data.baking.Baking
import kotlinx.coroutines.flow.Flow

interface BakingsRepository {
    fun getAllBakings(): Flow<List<Baking>>
    fun getBaking(id: Long): Flow<Baking>
    suspend fun insertBaking(baking: Baking)
    suspend fun updateBaking(baking: Baking)
    suspend fun deleteBaking(baking: Baking)
}