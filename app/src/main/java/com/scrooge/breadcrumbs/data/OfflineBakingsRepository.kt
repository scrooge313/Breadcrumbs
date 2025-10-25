package com.scrooge.breadcrumbs.data

import com.scrooge.breadcrumbs.baking.data.Baking
import com.scrooge.breadcrumbs.baking.data.BakingDao
import kotlinx.coroutines.flow.Flow

class OfflineBakingsRepository(private val bakingDao: BakingDao) : BakingsRepository{
    override fun getAllBakings(): Flow<List<Baking>> = bakingDao.getAllItems()

    override fun getAllBakings(id: Int): Flow<Baking> = bakingDao.getItem(id)

    override suspend fun insertBaking(baking: Baking) = bakingDao.insert(baking)

    override suspend fun updateBaking(baking: Baking) = bakingDao.update(baking)

    override suspend fun deleteBaking(baking: Baking) = bakingDao.delete(baking)
}