package com.scrooge.breadcrumbs.databaseimpl.di

import android.content.Context
import androidx.room.Room
import com.scrooge.breadcrumbs.databaseapi.data.baking.BakingDao
import com.scrooge.breadcrumbs.databaseimpl.data.BreadcrumbsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationProvisions {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BreadcrumbsDatabase {
        return Room.databaseBuilder(
            context,
            BreadcrumbsDatabase::class.java,
            "breadcrumbs_database"
        )
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    fun provideBakingsDao(database: BreadcrumbsDatabase): BakingDao {
        return database.bakingDao()
    }
}
