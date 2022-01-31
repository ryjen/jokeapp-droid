package com.github.ryjen.jokeapp.data.storage

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class Converters {
    @TypeConverter
    fun fromSQLDate(value: String?): Date? {
        return try {
            val d = value ?: return null
            fmt.parse(d)
        } catch (e: Exception) {
            null
        }
    }

    @TypeConverter
    fun toSQLDate(value: Date?): String? {
        val d = value ?: Date()
        return fmt.format(d)
    }

    companion object {
        val fmt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    }
}
