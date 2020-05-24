package com.kotlin.project.todo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.kotlin.project.todo.data.database.entity.User

@Dao
interface UserDao {

    @Insert
    fun inserUserDetails(user: User)
}