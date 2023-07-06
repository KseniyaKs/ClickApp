package com.example.clickapp.presentation.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [WinerEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun winerDao(): WinerDao?
}