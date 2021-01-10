package com.example.todomaneger

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
class Todo(

//    @PrimaryKey(autoGenerate = true) val id: Int,

    @PrimaryKey @ColumnInfo(name = "title") val title: String,

    @ColumnInfo(name = "due_date") val dueDate: String,

    @ColumnInfo(name = "detail") val detail: String
)