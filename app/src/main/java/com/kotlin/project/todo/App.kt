package com.kotlin.project.todo

import android.app.Application
import com.kotlin.project.todo.di.dataBaseModule
import com.kotlin.project.todo.di.repositoryModule
import com.kotlin.project.todo.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
                .modules(listOf(dataBaseModule, repositoryModule, viewModelModule))
        }
    }
}