package com.scrooge.breadcrumbs.databaseapi.data.baking

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BakingDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(baking: BakingEntity)

    @Update
    suspend fun update(baking: BakingEntity)

    @Delete
    suspend fun delete(baking: BakingEntity)

    @Query("SELECT * from bakings WHERE id = :id")
    fun getItem(id: Long): Flow<BakingEntity>

    @Query("SELECT * from bakings ORDER BY date DESC")
    fun getAllItems(): Flow<List<BakingEntity>>
}
