package com.scrooge.breadcrumbs.databaseimpl.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.scrooge.breadcrumbs.databaseapi.data.baking.BakingDao
import com.scrooge.breadcrumbs.databaseapi.data.baking.BakingEntity
import com.scrooge.breadcrumbs.databaseapi.data.baking.IngredientPerBakingEntity
import com.scrooge.breadcrumbs.databaseapi.data.ingredient.IngredientDao
import com.scrooge.breadcrumbs.databaseapi.data.ingredient.IngredientEntity
import com.scrooge.breadcrumbs.databaseapi.data.ingredient.MacroEntity
import com.scrooge.breadcrumbs.databaseapi.data.ingredient.MacroPerIngredientEntity
import com.scrooge.breadcrumbs.databaseapi.data.internationalization.LanguageEntity
import com.scrooge.breadcrumbs.databaseapi.data.internationalization.LanguageLocalizationEntity
import com.scrooge.breadcrumbs.databaseapi.data.internationalization.LocalizationEntity
import com.scrooge.breadcrumbs.databaseapi.data.internationalization.LocalizationDao
import com.scrooge.breadcrumbs.databaseimpl.data.converters.DateConverter

@Database(
    entities = [
        LanguageEntity::class,
        LocalizationEntity::class,
        LanguageLocalizationEntity::class,
        MacroEntity::class,
        IngredientEntity::class,
        MacroPerIngredientEntity::class,
        BakingEntity::class,
        IngredientPerBakingEntity::class,
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
