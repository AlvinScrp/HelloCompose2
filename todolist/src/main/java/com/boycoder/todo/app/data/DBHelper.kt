package com.boycoder.todo.app.data

import androidx.room.Room
import com.a.todolist.AppHolder

/**
 * @Author: zhutao
 * @desc:
 */
object DBHelper {
    private fun provideRoomDB() = Room.databaseBuilder(
        AppHolder.appContext,
        ToDoDatabase::class.java,
        "todo_db"
    ).build()

    fun provideDao(database: ToDoDatabase = provideRoomDB()) = database.toDoDao()
}