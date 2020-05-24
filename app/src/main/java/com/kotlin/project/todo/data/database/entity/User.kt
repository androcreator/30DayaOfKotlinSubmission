package com.kotlin.project.todo.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey var id:Int,
    var userName:String,
    var email:String,
    var passwored:String
)