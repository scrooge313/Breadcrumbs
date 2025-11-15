package com.scrooge.breadcrumbs.databaseapi.data.ingredient

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

//enum class Ingredient {
//    Water,
//    Oil,
//    Butter,
//    Egg,
//    Milk,
//
//    Flour,
//    Malt,
//    Sugar,
//    Salt,
//
//    // leaveners
//    Yeast,
//    SourdoughStarter,
//    BakingPowder,
//    BakingSoda,
//}
//
//enum class Macro {
//    Water,
//    Fat,
//    FatSaturated,
//    FatUnsaturated,
//    Carbs,
//    Sugars,
//    Salt,
//    Protein,
//}
//
//enum class Unit {
//    Grams,
//    Milliliters,
//    Amount,
//}


@Entity(
    tableName = "macros"
)
data class MacroEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo("name_localization_id")
    val nameLocalizationId: Long,
    @ColumnInfo("calories_per_100_gram")
    val caloriesPer100Grams: Float?,
)

@Entity(
    tableName = "macro_per_ingredient",
    primaryKeys = ["macro_id", "ingredient_id"],
    foreignKeys = [
        ForeignKey(
            entity = MacroEntity::class,
            parentColumns = ["id"],
            childColumns = ["macro_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = IngredientEntity::class,
            parentColumns = ["id"],
            childColumns = ["ingredient_id"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class MacroPerIngredientEntity(
    @ColumnInfo("macro_id")
    val macroId: Long,
    @ColumnInfo("ingredient_id")
    val ingredientId: Long,
    @ColumnInfo("grams_per_100_grams")
    val gramsPer100Grams: Float,
)

@Entity(
    tableName = "ingredients",
    foreignKeys = [
        ForeignKey(
            entity = IngredientEntity::class,
            parentColumns = ["id"],
            childColumns = ["generalized_ingredient_id"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class IngredientEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo("generalized_ingredient_id")
    val generalizedIngredientId: Long?,
    @ColumnInfo("name_localization_id")
    val nameLocalizationId: Long,
    @ColumnInfo("company")
    val company: String?,
    @ColumnInfo("calories_per_100_gram")
    val caloriesPer100Grams: Float?,
)

