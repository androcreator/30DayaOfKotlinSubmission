package com.kotlin.project.todo.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class BaseViewModel: ViewModel() {

    protected val ioScope = CoroutineScope(Dispatchers.Default+ SupervisorJob())

}