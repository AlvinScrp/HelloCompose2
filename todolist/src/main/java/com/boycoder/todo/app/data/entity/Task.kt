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
    var title: String,
    var desc: String,
    var isDone: Boolean
)

fun newTask() = Task(0, "", "", false)