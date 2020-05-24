package com.kotlin.project.todo.repository

import androidx.lifecycle.LiveData
import com.kotlin.project.todo.data.database.dao.ToDoDao
import com.kotlin.project.todo.data.database.entity.ToDoItems
import org.koin.core.KoinComponent
import org.koin.core.inject

class TaskRepository:KoinComponent {

    private val toDoDao: ToDoDao by inject()

     fun createTask(toDoItems:ToDoItems){
        toDoDao.inserTodo(toDoItems)
    }

    fun getTaskList(): LiveData<List<ToDoItems>>? {
        return toDoDao.getTodoList()
    }
}