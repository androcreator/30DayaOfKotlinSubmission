package com.kotlin.project.todo.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kotlin.project.todo.data.database.entity.ToDoItems

@Dao
interface ToDoDao {

    @Insert
    fun inserTodo(user: ToDoItems)

    @Query("SELECT * FROM ToDoItems")
    fun getTodoList():LiveData<List<ToDoItems>>?
}