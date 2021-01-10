package com.example.todomaneger

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_table")
    fun getAll(): Flow<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todo: Todo)

    @Delete
    suspend fun deleteTodo(vararg todos: Todo)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAll()
}