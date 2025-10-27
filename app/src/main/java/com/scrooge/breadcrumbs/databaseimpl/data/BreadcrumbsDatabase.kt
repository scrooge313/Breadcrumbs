package com.scrooge.breadcrumbs.databaseimpl.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.scrooge.breadcrumbs.databaseapi.data.baking.Baking
import com.scrooge.breadcrumbs.databaseapi.data.baking.BakingDao
import com.scrooge.breadcrumbs.databaseimpl.data.converters.DateConverter

@Database(entities = [Baking::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class BreadcrumbsDatabase : RoomDatabase() {
    abstract fun bakingDao() : BakingDao
}
