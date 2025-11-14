package com.scrooge.breadcrumbs.databaseimpl.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.scrooge.breadcrumbs.databaseapi.data.baking.Baking
import com.scrooge.breadcrumbs.databaseapi.data.baking.BakingDao
import com.scrooge.breadcrumbs.databaseapi.data.ingredient.Ingredient
import com.scrooge.breadcrumbs.databaseapi.data.ingredient.IngredientPerBaking
import com.scrooge.breadcrumbs.databaseapi.data.ingredient.IngredientDao
import com.scrooge.breadcrumbs.databaseapi.data.ingredient.Macro
import com.scrooge.breadcrumbs.databaseapi.data.ingredient.MacroPerIngredient
import com.scrooge.breadcrumbs.databaseapi.data.internationalization.Language
import com.scrooge.breadcrumbs.databaseapi.data.internationalization.Localization
import com.scrooge.breadcrumbs.databaseapi.data.internationalization.LocalizationDao
import com.scrooge.breadcrumbs.databaseimpl.data.converters.DateConverter

@Database(
    entities = [
        Language::class,
        Localization::class,
        Macro::class,
        MacroPerIngredient::class,
        Ingredient::class,
        IngredientPerBaking::class,
        Baking::class
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(DateConverter::class)
abstract class BreadcrumbsDatabase : RoomDatabase() {
    abstract fun localizationDao(): LocalizationDao
    abstract fun ingredientDao(): IngredientDao
    abstract fun bakingDao(): BakingDao
}
