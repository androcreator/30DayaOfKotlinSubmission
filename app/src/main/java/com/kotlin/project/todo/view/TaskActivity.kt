package com.kotlin.project.todo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.project.R
import com.kotlin.project.todo.data.database.dao.UserDao
import com.kotlin.project.todo.viewmodel.TaskViewModel

import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class TaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
//        setSupportActionBar(this)
    }
}
