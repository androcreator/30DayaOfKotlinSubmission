package com.kotlin.project.todo.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kotlin.project.todo.data.database.STATUS
import com.kotlin.project.todo.data.database.entity.ToDoItems

@Dao
interface ToDoDao {

    @Insert
    fun inserTodo(user: ToDoItems)

    @Query("SELECT * FROM ToDoItems ORDER BY dateTime ASC")
    fun getTodoList():LiveData<List<ToDoItems>>

    @Query("DELETE FROM ToDoItems WHERE id=:tid")
    fun deleteTodo(tid:Int?)


    @Query("UPDATE ToDoItems SET  taskName = :taskName , tag =:tag , `desc` =:desc , isRemainder =:isRemainder , dateTime =:dateTime , status =:status WHERE id = :tid")
    fun updateTask(tid:Int?, taskName:String, tag:String, desc:String,isRemainder:Boolean,dateTime:Long,status: STATUS)
}