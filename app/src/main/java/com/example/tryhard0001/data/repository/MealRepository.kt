package com.example.tryhard0001.data.repository

import androidx.lifecycle.LiveData
import com.example.tryhard0001.data.db.model.Meal

interface MealRepository {
    suspend fun getTodayMeal(): LiveData<List<Meal>>
    suspend fun insert(meal: Meal)
    suspend fun delete(meal: Meal)
}