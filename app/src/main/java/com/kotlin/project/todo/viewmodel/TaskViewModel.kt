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

    fun getTaskList() = taskRepository.getTaskList()


    data class Task(var taskName:String, var date:Long, var tag:String, var desc:String, var isRemainder:Boolean)
}