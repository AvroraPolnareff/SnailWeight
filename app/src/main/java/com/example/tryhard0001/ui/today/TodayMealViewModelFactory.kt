package com.example.tryhard0001.ui.today

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tryhard0001.data.repository.MealRepository

class TodayMealViewModelFactory(
    private val mealRepository: MealRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TodayMealViewModel(mealRepository) as T
    }
}