package com.scrooge.breadcrumbs.databaseapi.data.baking

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bakings")
data class Baking(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
//    val date: OffsetDateTime,
//    val observations: List<String>,
    // todo baking process
)
