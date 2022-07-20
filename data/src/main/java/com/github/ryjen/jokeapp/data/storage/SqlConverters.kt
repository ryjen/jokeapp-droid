package com.github.ryjen.jokeapp.data.storage

import com.squareup.sqldelight.ColumnAdapter
import java.text.SimpleDateFormat
import java.util.*

fun datesAdapter(
    locale: Locale = Locale.getDefault()
) = object : ColumnAdapter<Date, String> {

    private val fmt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", locale)

    override fun decode(databaseValue: String): Date {
        return try {
            fmt.parse(databaseValue) ?: Date()
        } catch (e: Exception) {
            Date()
        }
    }

    override fun encode(value: Date): String {
        return fmt.format(value)
    }
}
