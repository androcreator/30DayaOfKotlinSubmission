package com.kotlin.project.todo.di

import com.kotlin.project.todo.viewmodel.TaskViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { TaskViewModel() }
}