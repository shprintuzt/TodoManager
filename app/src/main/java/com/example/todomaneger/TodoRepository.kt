package com.example.todomaneger

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class TodoRepository(private val todoDao: TodoDao) {

    val allTodos: Flow<List<Todo>> = todoDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(todo: Todo) {
        todoDao.insert(todo)
    }

//    @Suppress("RedundantSuspendModifier")
//    @WorkerThread
//    suspend fun delete(vararg todos: Todo) {
//        todoDao.deleteTodos(*todos)
//    }
}