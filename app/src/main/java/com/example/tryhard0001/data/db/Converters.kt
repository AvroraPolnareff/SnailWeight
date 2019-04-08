package com.example.tryhard0001.data.db

import androidx.room.TypeConverter
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

object Converters {
    private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    @TypeConverter
    @JvmStatic
    fun toOffsetDateTime ( value: String?): OffsetDateTime? = value?.let {
        return formatter.parse(value, OffsetDateTime::from)
    }
    @TypeConverter
    @JvmStatic
    fun fromOffsetDateTime (date: OffsetDateTime?): String? = date?.format(formatter)
}