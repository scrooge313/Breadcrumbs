package com.scrooge.breadcrumbs.databaseapi.data.internationalization

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "localizations",
    primaryKeys = ["language_id", "localization_id"],
    foreignKeys = [
        ForeignKey(
            entity = Language::class,
            parentColumns = ["id"],
            childColumns = ["language_id"],
            onDelete = ForeignKey.CASCADE,
        )
    ],
)
data class Localization(
    @ColumnInfo("language_id")
    val languageId: Short,
    @ColumnInfo("localization_id")
    val localizationId: Long,
    val value: String,
)
