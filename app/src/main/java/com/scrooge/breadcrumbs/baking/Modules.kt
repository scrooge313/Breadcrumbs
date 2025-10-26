package com.scrooge.breadcrumbs.baking

import android.content.Context
import androidx.room.Room
import com.scrooge.breadcrumbs.baking.data.BakingDao
import com.scrooge.breadcrumbs.data.BakingsRepository
import com.scrooge.breadcrumbs.data.BreadcrumbsDatabase
import com.scrooge.breadcrumbs.data.OfflineBakingsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApplicationBindings {
    @Binds
    abstract fun bindBakingsRepository(
        bakingsRepository: OfflineBakingsRepository
    ): BakingsRepository
}

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
