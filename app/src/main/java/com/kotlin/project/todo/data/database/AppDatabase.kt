package com.kotlin.project.todo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kotlin.project.todo.data.database.dao.ToDoDao
import com.kotlin.project.todo.data.database.dao.UserDao
import com.kotlin.project.todo.data.database.entity.EnumTypeConverter
import com.kotlin.project.todo.data.database.entity.ToDoItems
import com.kotlin.project.todo.data.database.entity.User

@Database(entities =[User::class,ToDoItems::class],version = 1, exportSchema = false )
@TypeConverters(EnumTypeConverter::class)
abstract class AppDatabase:RoomDatabase() {
    abstract fun userDetailsDao():UserDao
    abstract fun todoDetailsDao():ToDoDao
}