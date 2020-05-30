package com.kotlin.project.todo.interfaces

import com.kotlin.project.todo.data.database.entity.ToDoItems

interface ITaskItemClickCallback {

    fun onTaskItemClick(toToDoItems: ToDoItems)
}