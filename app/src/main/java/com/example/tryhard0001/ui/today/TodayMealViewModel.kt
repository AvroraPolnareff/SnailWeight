package com.example.tryhard0001.ui.today

import androidx.lifecycle.ViewModel
import com.example.tryhard0001.data.db.model.Meal
import com.example.tryhard0001.data.repository.MealRepository
import com.example.tryhard0001.internal.lazyDeferred

class TodayMealViewModel(
    private val mealRepository: MealRepository
) : ViewModel() {

    val todayMealEntries by lazyDeferred {
        mealRepository.getTodayMeal()
    }
    suspend fun insertMeal(meal: Meal) = mealRepository.insert(meal)
    fun deleteMeal(meal: Meal) = lazyDeferred { mealRepository.delete(meal)}

}
