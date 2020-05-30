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

    fun getTaskList(): LiveData<List<ToDoItems>> {
        return toDoDao.getTodoList()
    }

    fun deleteTask(id:Int){
        return toDoDao.deleteTodo(id)
    }

    fun updateTask(toDoItems:ToDoItems) {
        return toDoDao.updateTask(
                toDoItems.id,
                toDoItems.taskName,
                toDoItems.tag,
                toDoItems.desc,
                toDoItems.isRemainder,
                toDoItems.dateTime,
                toDoItems.status
        )
    }
}