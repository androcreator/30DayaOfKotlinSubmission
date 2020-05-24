package com.kotlin.project.todo.di

import com.kotlin.project.todo.repository.TaskRepository
import org.koin.dsl.module

val repositoryModule = module {

    single { TaskRepository() }
}