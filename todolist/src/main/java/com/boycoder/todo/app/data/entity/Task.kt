package com.boycoder.todo.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Author: zhutao
 * @desc:
 */

@Entity(tableName = "todo_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val desc: String,
    val isDone: Boolean
)