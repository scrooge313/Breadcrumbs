package com.scrooge.breadcrumbs.databaseapi.data.baking

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.scrooge.breadcrumbs.databaseapi.data.ingredient.IngredientEntity
import java.time.OffsetDateTime

@Entity(
    tableName = "ingredients_per_baking", // todo rename to plural
    primaryKeys = ["baking_id", "ingredient_id"],
    foreignKeys = [
        ForeignKey(
            entity = BakingEntity::class,
            parentColumns = ["id"],
            childColumns = ["baking_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = IngredientEntity::class,
            parentColumns = ["id"],
            childColumns = ["ingredient_id"],
            onDelete = ForeignKey.CASCADE
        ),
    ],
    indices = [
        Index("baking_id"),
        Index("ingredient_id"),
    ],
)
data class IngredientPerBakingEntity(
    @ColumnInfo("baking_id")
    val bakingId: Long,
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
//    val recipe: List<RecipeStep>,
//    val observations: List<String>,
    // todo baking process
    // todo timestamped photos
)
