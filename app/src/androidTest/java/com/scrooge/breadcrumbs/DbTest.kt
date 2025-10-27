package com.scrooge.breadcrumbs

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.scrooge.breadcrumbs.databaseapi.data.baking.Baking
import com.scrooge.breadcrumbs.databaseapi.data.baking.BakingDao
import com.scrooge.breadcrumbs.databaseimpl.data.BreadcrumbsDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DbTest {
    private lateinit var bakingDao: BakingDao
    private lateinit var breadcrumbsDatabase: BreadcrumbsDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        breadcrumbsDatabase = Room.inMemoryDatabaseBuilder(context, BreadcrumbsDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        bakingDao = breadcrumbsDatabase.bakingDao()
    }

    @After
    fun closeDb() {
        breadcrumbsDatabase.close()
    }

    private val baking1 = Baking(1, "Sourdough")
    private val baking2 = Baking(2, "Brioche")

    @Test
    fun daoInsert_insertsBakingIntoDb() = runTest {
        bakingDao.insert(baking1)
        val allItems = bakingDao.getAllItems().first()
        assertEquals(listOf(baking1), allItems)
    }

}