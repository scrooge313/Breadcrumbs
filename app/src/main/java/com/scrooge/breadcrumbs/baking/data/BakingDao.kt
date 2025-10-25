package com.scrooge.breadcrumbs.baking.data

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
    suspend fun insert(baking: Baking)

    @Update
    suspend fun update(baking: Baking)

    @Delete
    suspend fun delete(baking: Baking)

    @Query("SELECT * from bakings WHERE id = :id")
    fun getItem(id: Int): Flow<Baking>

    @Query("SELECT * from bakings ORDER BY date DESC")
    fun getAllItems(): Flow<List<Baking>>
}
