package com.example.tryhard0001.data.repository

import androidx.lifecycle.LiveData
import com.example.tryhard0001.data.db.dao.MealDao
import com.example.tryhard0001.data.db.model.Meal
import org.threeten.bp.OffsetDateTime

class MealRepositoryImpl(val mealDao: MealDao) : MealRepository {
    override suspend fun getTodayMeal(): LiveData<List<Meal>> {
        return mealDao.getMealByDate(OffsetDateTime.now(), OffsetDateTime.now().minusDays(1) )
    }

    override suspend fun insert(meal: Meal) = mealDao.insertMeal(meal)

    override suspend fun delete(meal: Meal) = mealDao.deleteMeal(meal)
}