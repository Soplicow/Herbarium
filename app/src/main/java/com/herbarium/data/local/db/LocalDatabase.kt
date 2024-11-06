package com.herbarium.data.local.db

import android.content.Context
import androidx.room.Database
import com.herbarium.data.local.model.Plant
import androidx.room.Room
import androidx.room.RoomDatabase
import com.herbarium.data.local.model.User

@Database(entities = [Plant::class, User::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun plantDao(): PlantDao  // Create a DAO for interacting with the Plant table in the database.
    abstract fun userDao(): UserDao  // Create a DAO for interacting with the User table in the database.

    companion object {
        @Volatile
        private var INSTANCE: LocalDatabase? = null

        fun getDatabase(context: Context): LocalDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,
                    "plant_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}