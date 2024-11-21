package com.example.devmobile

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Airport::class, Favorite::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun flightDao(): FlightDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .createFromAsset("database/flight_search.db")
                    .build()
                    .also{Instance = it}

            }
        }
    }
}
