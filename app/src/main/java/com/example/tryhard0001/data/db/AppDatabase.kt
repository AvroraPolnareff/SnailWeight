package com.example.tryhard0001.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tryhard0001.data.db.dao.MealDao
import com.example.tryhard0001.data.db.model.Meal


@Database(entities = [Meal::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun mealDao(): MealDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "futureWeatherEntries.db"
            )
                .build()



//        fun getDatabase(context: Context): AppDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    "app_database"
//                ).build()
//                INSTANCE = instance
//                instance
//             }
//        }
    }

}