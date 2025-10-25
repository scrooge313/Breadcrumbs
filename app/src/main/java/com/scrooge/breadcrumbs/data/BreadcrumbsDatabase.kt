package com.scrooge.breadcrumbs.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.scrooge.breadcrumbs.baking.data.Baking
import com.scrooge.breadcrumbs.baking.data.BakingDao

@Database(entities = [Baking::class], version = 1, exportSchema = false)
abstract class BreadcrumbsDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var Instance: BreadcrumbsDatabase? = null

        fun getDatabase(context: Context): BreadcrumbsDatabase = Instance ?: synchronized(this) {
            Room.databaseBuilder(context, BreadcrumbsDatabase::class.java, "breadcrumbs_database")
                .fallbackToDestructiveMigration(false)
                .build()
                .also { Instance = it }
        }
    }

    abstract fun bakingDao() : BakingDao
}