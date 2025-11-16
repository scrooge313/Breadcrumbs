package com.scrooge.breadcrumbs.databaseapi.data.ingredient

import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

data class IngredientWithMacrosDbState(
    @Embedded
    val ingredient: IngredientEntity,
    @Relation(
        entity = MacroPerIngredientEntity::class,
        parentColumn = "id",
        entityColumn = "ingredient_id",
    )
    val macroPerIngredients: List<MacroPerIngredientWithMacroDbState>
)

data class MacroPerIngredientWithMacroDbState(
    @Embedded
    val macroPerIngredient: MacroPerIngredientEntity,
    @Relation(
        entity = MacroEntity::class,
        parentColumn = "macro_id",
        entityColumn = "id"
    )
    val macro: MacroEntity,
)

@Dao
interface IngredientDao {
    @Transaction
    @Query("SELECT * FROM ingredients")
    fun getAllIngredients(): Flow<List<IngredientWithMacrosDbState>>

    @Query("SELECT * FROM ingredients WHERE id = :id")
    fun getIngredient(id: Long): Flow<IngredientWithMacrosDbState>
}
