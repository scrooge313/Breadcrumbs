package com.scrooge.breadcrumbs.databaseapi.data.baking

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime

@Entity(tableName = "bakings")
data class Baking(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val date: OffsetDateTime,
//    val observations: List<String>,
    // todo baking process
    // todo timestamped photos
)
