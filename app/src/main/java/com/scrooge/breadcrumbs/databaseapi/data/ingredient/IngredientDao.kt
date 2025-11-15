package com.scrooge.breadcrumbs.databaseapi.data.ingredient

import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

data class StructuredIngredient(
    @Embedded
    val ingredient: IngredientEntity,
    val name: String,
    @Relation(
        parentColumn = "id",
        entityColumn = "ingredient_id"
    )
    val macroPerIngredients: List<MacroPerIngredientEntity>
)

@Dao
interface IngredientDao {
    @Transaction
    @Query("""
        SELECT
            ing.*,
            ing_loc.value as name
        FROM ingredients ing
        INNER JOIN localizations ing_loc ON ing.name_localization_id = ing_loc.localization_id
        INNER JOIN languages lang ON ing_loc.language_id = lang.id
        AND lang.language_code = 'de' and :languageCode = :languageCode
    """)
    fun getAllIngredients(languageCode: String): Flow<List<StructuredIngredient>>
}
