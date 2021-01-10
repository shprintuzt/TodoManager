package com.example.todomaneger

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Todo::class), version = 1, exportSchema = false)
public abstract class TodoRoomDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao

    private class TodoDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.todoDao())
                }
            }
        }

        suspend fun populateDatabase(todoDao: TodoDao) {
            todoDao.deleteAll()

            var todo = Todo(
//                id = 0,
                title = "論文作成",
                dueDate = "20210103",
                detail = "論文を書く"
            )
            todoDao.insert(todo)
            todo = Todo(
//                id = 0,
                title = "アプリ作成",
                dueDate = "20210220",
                detail = "アプリを作る"
            )
            todoDao.insert(todo)

        }
    }

    companion object {
        @Volatile
        private var INSTANCE: TodoRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): TodoRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoRoomDatabase::class.java,
                    "todo_database"
                )
                    .addCallback(TodoDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}