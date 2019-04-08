package com.example.tryhard0001.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tryhard0001.data.db.model.Meal
import org.threeten.bp.OffsetDateTime

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insertMeal(meal: Meal)
    @Delete
    fun deleteMeal(meal: Meal)
    @Query("SELECT * from meal where date between date(:fromDate) and date(:toDate)")
    fun getMealByDate(fromDate: OffsetDateTime?, toDate: OffsetDateTime?): LiveData<List<Meal>>
}