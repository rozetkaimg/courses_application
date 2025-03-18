package com.rozetka.storage.di

import androidx.room.Room
import com.rozetka.storage.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "app_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().coursesDao() }
}