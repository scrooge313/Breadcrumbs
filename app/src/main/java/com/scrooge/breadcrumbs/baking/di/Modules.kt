package com.scrooge.breadcrumbs.baking.di

import com.scrooge.breadcrumbs.baking.data.BakingsRepository
import com.scrooge.breadcrumbs.baking.data.OfflineBakingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ApplicationBindings {
    @Binds
    abstract fun bindBakingsRepository(
        bakingsRepository: OfflineBakingsRepository
    ): BakingsRepository
}

