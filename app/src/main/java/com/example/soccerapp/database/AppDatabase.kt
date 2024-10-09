// AppDatabase.kt
package com.example.soccerapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.soccerapp.model.Team

@Database(entities = [Team::class], version = 2, exportSchema = false) // Incrementa la versión aquí
abstract class AppDatabase : RoomDatabase() {
    abstract fun teamDao(): TeamDao
}
