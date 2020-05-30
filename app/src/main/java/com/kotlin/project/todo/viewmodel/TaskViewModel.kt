package com.kotlin.project.todo.viewmodel

import androidx.lifecycle.viewModelScope
import com.kotlin.project.todo.data.database.entity.ToDoItems
import com.kotlin.project.todo.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

class TaskViewModel : BaseViewModel(), KoinComponent {
    private val taskRepository: TaskRepository by inject()

    fun getTaskList() = taskRepository.getTaskList()

    fun addTask(toDoItems: ToDoItems) {
        viewModelScope.launch {
            createTask(toDoItems)
        }
    }

    private suspend fun createTask(toDoItems: ToDoItems) {
        withContext(Dispatchers.IO) {
            taskRepository.createTask(toDoItems)
        }
    }


    fun update(toDoItems: ToDoItems) {
        viewModelScope.launch {
            updateTask(toDoItems)
        }
    }


    private suspend fun updateTask(toDoItems: ToDoItems) {
        withContext(Dispatchers.IO) {
            taskRepository.updateTask(toDoItems)
        }
    }


    fun delete(id: () -> Int) {
        viewModelScope.launch {
            deleteTask(id.invoke())
        }
    }


    private suspend fun deleteTask(id: Int) {
        withContext(Dispatchers.IO) {
            taskRepository.deleteTask(id)
        }
    }

}