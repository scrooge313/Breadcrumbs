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
//@Entity(tableName = "ingredient_specializations")
//data class IngredientSpecialization(
//    @PrimaryKey(autoGenerate = true)
//    val id: Long = 0,
//    val ingredient: Ingredient,
//    val description: String,
//    // todo macros here? or implicit?
//)
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
//@Entity(
//    tableName = "macros_per_100_g",
//    foreignKeys = [
//        ForeignKey(
//            entity = IngredientSpecialization::class,
//            parentColumns = ["id"],
//            childColumns = ["ingredient_specialization_id"],
//            onDelete = ForeignKey.CASCADE
//        )
//    ]
//)
//data class Macros(
//    @PrimaryKey(autoGenerate = true)
//    val id: Long = 0,
//    val ingredientSpecialization: IngredientSpecialization,
//    val macro: Macro,
//    val valueInGrams: Float,
//)
//
//enum class Unit {
//    Grams,
//    Milliliters,
//    Amount,
//}
//
//@Entity(
//    tableName = "ingredient_amounts",
//    foreignKeys = [
//        ForeignKey(
//            entity = IngredientSpecialization::class,
//            parentColumns = ["id"],
//            childColumns = ["ingredient_specialization_id"],
//            onDelete = ForeignKey.CASCADE
//        )
//    ]
//)
//data class IngredientAmount(
//    val id: Long,
//    val ingredient: Ingredient,
//    val specification: String,
//    val amount: String,
//    val unit: Unit,
//)

@Entity(
    tableName = "macros"
)
data class Macro(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo("name_localization_id")
    val nameLocalizationId: Long,
    @ColumnInfo("calories_per_100_gram")
    val caloriesPer100Grams: Float?,
)

@Entity(
    tableName = "macro_amounts",
    foreignKeys = [
        ForeignKey(
            entity = Macro::class,
            parentColumns = ["id"],
            childColumns = ["macro_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Ingredient::class,
            parentColumns = ["id"],
            childColumns = ["ingredient_id"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class MacroAmount(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo("macro_id") val macroId: Long?,
    @ColumnInfo("ingredient_id") val ingredientId: Long,
    @ColumnInfo("grams_per_100_grams") val gramsPer100Grams: Float,
)

@Entity(
    tableName = "ingredients",
    foreignKeys = [
        ForeignKey(
            entity = Ingredient::class,
            parentColumns = ["id"],
            childColumns = ["generalized_ingredient_id"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class Ingredient(
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

@Entity(
    tableName = "ingredient_amounts",
    foreignKeys = [
        ForeignKey(
            entity = Ingredient::class,
            parentColumns = ["id"],
            childColumns = ["ingredient_id"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class IngredientAmount(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo("ingredient_id")
    val ingredientId: Long,
    @ColumnInfo("grams")
    val grams: Float,
    // todo reference recipe or recipe step via fk
)
