package com.scrooge.breadcrumbs.baking.domain.state

import java.time.OffsetDateTime

data class BakingDetails(
    val bakingId: Long,
    val name: String,
    val date: OffsetDateTime,
    val ingredients: List<IngredientAmountDetails>,
)

data class IngredientAmountDetails(
    val ingredientId: Long,
    val name: String,
    val macros: List<MacroAmountDetails>,
    val caloriesPer100Grams: Float,
    val grams: Float,
)

data class MacroAmountDetails(
    val macroId: Long,
    val name: String,
    val caloriesPer100Grams: Float,
)
