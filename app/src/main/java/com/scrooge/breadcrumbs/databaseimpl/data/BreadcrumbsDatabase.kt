package com.scrooge.breadcrumbs.databaseimpl.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.scrooge.breadcrumbs.databaseapi.data.baking.Baking
import com.scrooge.breadcrumbs.databaseapi.data.baking.BakingDao

@Database(entities = [Baking::class], version = 1, exportSchema = false)
abstract class BreadcrumbsDatabase : RoomDatabase() {
    abstract fun bakingDao() : BakingDao
}
