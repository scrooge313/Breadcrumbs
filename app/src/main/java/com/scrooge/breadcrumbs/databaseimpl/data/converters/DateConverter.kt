package com.scrooge.breadcrumbs.databaseimpl.data.converters

import androidx.room.TypeConverter
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class DateConverter {
    companion object {
        private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    }

    @TypeConverter
    fun fromOffsetDateTime(date: OffsetDateTime): String {
        return date.format(formatter)
    }

    @TypeConverter
    fun toOffsetDateTime(isoDate: String): OffsetDateTime {
        return formatter.parse(isoDate, OffsetDateTime::from)

    }
}
