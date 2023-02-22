package com.example.m15_room

import android.app.Application
import androidx.room.Room

class App: Application() {
    lateinit var db: AppDataBase

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "db"
        )
//            .addMigrations(Migration_1_2)
            .build()
    }
}