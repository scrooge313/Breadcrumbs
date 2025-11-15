package com.scrooge.breadcrumbs.databaseapi.data.internationalization

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    "languages",
)
data class LanguageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Short = 0,
    @ColumnInfo("language_code")
    val languageCode: String,
)
