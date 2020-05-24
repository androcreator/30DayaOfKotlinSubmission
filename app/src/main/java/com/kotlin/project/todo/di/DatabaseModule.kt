package com.kotlin.project.todo.di

import androidx.room.Room
import com.kotlin.project.todo.data.database.AppDatabase
import org.koin.dsl.module

val dataBaseModule = module {

    single { Room.databaseBuilder(get(), AppDatabase::class.java, "todo.db").allowMainThreadQueries().build() }
    single { get<AppDatabase>().userDetailsDao() }
    single { get<AppDatabase>().todoDetailsDao() }
}

