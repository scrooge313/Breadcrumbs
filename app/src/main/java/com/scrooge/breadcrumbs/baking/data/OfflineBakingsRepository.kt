package com.scrooge.breadcrumbs.baking.data

import com.scrooge.breadcrumbs.baking.domain.repositories.BakingsRepository
import com.scrooge.breadcrumbs.baking.domain.state.BakingDetails
import com.scrooge.breadcrumbs.baking.domain.state.BakingMetaData
import com.scrooge.breadcrumbs.baking.domain.state.IngredientAmountDetails
import com.scrooge.breadcrumbs.baking.domain.state.MacroAmountDetails
import com.scrooge.breadcrumbs.databaseapi.data.baking.BakingDao
import com.scrooge.breadcrumbs.databaseapi.data.baking.BakingEntity
import com.scrooge.breadcrumbs.databaseapi.data.baking.BakingWithIngredientsDbState
import com.scrooge.breadcrumbs.databaseapi.data.internationalization.LocalizationDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OfflineBakingsRepository @Inject constructor(
    private val localizationDao: LocalizationDao,
    private val bakingDao: BakingDao,
) : BakingsRepository {
    override fun getAllBakings(): Flow<List<BakingMetaData>> =
        bakingDao
            .getAllItems()
            .map { bakings ->
                bakings.map(BakingEntity::toMetaData)
            }
            .flowOn(Dispatchers.Main)

    override fun getBakingDetails(bakingId: Long): Flow<BakingDetails> =
        bakingDao
            .getItem(bakingId)
            .map(BakingWithIngredientsDbState::toDetails)
            .flowOn(Dispatchers.Main)

}

private fun BakingEntity.toMetaData() = BakingMetaData(
    bakingId = this.id,
    name = this.name,
    date = this.date,
)

private fun BakingWithIngredientsDbState.toDetails() = BakingDetails(
    bakingId = this.baking.id,
    name = this.baking.name,
    date = this.baking.date,
//    ingredients = emptyList(),
    ingredients = this.ingredients.map { IngredientAmountDetails(
        ingredientId = it.ingredient.ingredient.id,
        name = "TODO",
        macros = it.ingredient.macroPerIngredients.map { macro -> MacroAmountDetails(
            macroId = macro.macro.id,
            name = "TODO",
            caloriesPer100Grams = macro.macro.caloriesPer100Grams
        ) },
        caloriesPer100Grams = it.ingredient.ingredient.caloriesPer100Grams!!, //todo make not nullable
        grams = it.ingredientPerBaking.grams
    ) }, // todo
)
