package com.example.clickapp.presentation

import android.app.Application
import androidx.room.Room.databaseBuilder
import com.example.clickapp.presentation.database.AppDatabase


class App : Application() {

    var database: AppDatabase? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = databaseBuilder(this, AppDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()
    }

    companion object {
        var instance: App? = null
    }
}