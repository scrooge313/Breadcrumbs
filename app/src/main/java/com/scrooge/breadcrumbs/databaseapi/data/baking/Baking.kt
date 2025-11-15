package com.scrooge.breadcrumbs.databaseapi.data.baking

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.scrooge.breadcrumbs.databaseapi.data.ingredient.IngredientEntity
import java.time.OffsetDateTime

@Entity(
    tableName = "ingredient_per_baking",
    foreignKeys = [
        ForeignKey(
            entity = IngredientEntity::class,
            parentColumns = ["id"],
            childColumns = ["ingredient_id"],
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class IngredientPerBakingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo("ingredient_id")
    val ingredientId: Long,
    @ColumnInfo("grams")
    val grams: Float,
    // todo reference recipe or recipe step via fk
)

@Entity(tableName = "bakings")
data class BakingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val date: OffsetDateTime,
//    val ingredients: List<IngredientAmount>,
//    val recipe: List<RecipeStep>,
//    val observations: List<String>,
    // todo baking process
    // todo timestamped photos
)

@Entity(tableName = "recipe_steps")
data class RecipeStepEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val description: String,
//    val ingredients: List<IngredientAmount>,
//    val predecessors: List<RecipeStep>,
    val result: String
)