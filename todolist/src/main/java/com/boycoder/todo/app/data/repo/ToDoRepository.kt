package com.boycoder.todo.app.data.repo

import com.boycoder.todo.app.data.DBHelper
import com.boycoder.todo.app.data.ToDoDao
import com.boycoder.todo.app.data.entity.Task
import kotlinx.coroutines.flow.Flow


/**
 * @Author: zhutao
 * @desc:
 */

class ToDoRepository(private val toDoDao: ToDoDao = DBHelper.provideDao()) {

    val getAllTasks: Flow<List<Task>> = toDoDao.loadAllTasks()

      fun searchTask(taskId: Int):Flow<Task?>  {
        return toDoDao.searchTask(taskId = taskId)
    }

    suspend fun insertTask(task: Task) {
        toDoDao.insertTask(task = task)
    }

    suspend fun updateTask(task: Task) {
        toDoDao.updateTask(task = task)
    }

    suspend fun deleteTaskById(taskId: Int) {
        toDoDao.deleteTaskById(taskId = taskId)
    }

    suspend fun deleteTask(task: Task) {
        toDoDao.deleteTask(task = task)
    }

    suspend fun deleteAllTasks() {
        toDoDao.deleteAllTasks()
    }
}