package com.example.tryhard0001.data.db.model

import androidx.room.*
import org.threeten.bp.OffsetDateTime

@Entity
data class Meal (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "meal_id")
    val id: Int = 0,

    var name: String,

    var calories: Int,

    var ingredientsListId: Int?,

    var grams: Int? = null,

    var caloriesPerHundred: Int? = null,

    var unique: Boolean = false,

    val date: OffsetDateTime? = null
)