package com.example.tryhard0001

import android.app.Application
import com.example.tryhard0001.data.db.AppDatabase
import com.example.tryhard0001.data.repository.MealRepository
import com.example.tryhard0001.data.repository.MealRepositoryImpl
import com.example.tryhard0001.ui.today.TodayMealViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class TryHardApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@TryHardApplication))

        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { instance<AppDatabase>().mealDao()}
        bind<MealRepository>() with singleton { MealRepositoryImpl(instance()) }
        bind() from provider { TodayMealViewModelFactory(instance()) }
    }
}