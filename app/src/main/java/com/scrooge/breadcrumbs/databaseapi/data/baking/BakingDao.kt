package com.scrooge.breadcrumbs.databaseapi.data.baking

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Update
import com.scrooge.breadcrumbs.databaseapi.data.ingredient.IngredientEntity
import com.scrooge.breadcrumbs.databaseapi.data.ingredient.IngredientWithMacrosDbState
import kotlinx.coroutines.flow.Flow

data class BakingWithIngredientsDbState(
    @Embedded
    val baking: BakingEntity,
    @Relation(
        entity = IngredientPerBakingEntity::class,
        parentColumn = "id",
        entityColumn = "baking_id",
    )
    val ingredients: List<IngredientPerBakingDbState>
)

data class IngredientPerBakingDbState(
    @Embedded
    val ingredientPerBaking: IngredientPerBakingEntity,
    @Relation(
        entity = IngredientEntity::class,
        parentColumn = "ingredient_id",
        entityColumn = "id"
    )
    val ingredient: IngredientWithMacrosDbState
)

@Dao
interface BakingDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(baking: BakingEntity)

    @Update
    suspend fun update(baking: BakingEntity)

    @Delete
    suspend fun delete(baking: BakingEntity)

    @Query("""
        SELECT b.* from bakings b
        WHERE id = :id
    """)
    fun getItem(id: Long): Flow<BakingWithIngredientsDbState>

    @Query("SELECT * from bakings ORDER BY date DESC")
    fun getAllItems(): Flow<List<BakingEntity>>
}
