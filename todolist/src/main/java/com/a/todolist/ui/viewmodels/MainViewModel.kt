package com.a.todolist.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.SnapshotMutationPolicy
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a.todolist.consts.RequestState
import com.boycoder.todo.app.data.entity.Task
import com.boycoder.todo.app.data.entity.newTask
import com.boycoder.todo.app.data.repo.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * @Author: zhutao
 * @desc:
 */
class MainViewModel(
    private val repository: ToDoRepository = ToDoRepository()
) : ViewModel() {


    private val _allTasks = MutableStateFlow<RequestState<List<Task>>>(RequestState.Idle)
    val allTasks: StateFlow<RequestState<List<Task>>> = _allTasks

    init {
        loadAllTasks()
    }

    private fun loadAllTasks() {
        viewModelScope.launch {
            repository.getAllTasks
                .onStart { _allTasks.value = RequestState.Loading }
                .catch { _allTasks.value = RequestState.Error(it) }
                .collect {
                    Log.d("alvin","XX --- ")
                    _allTasks.value = RequestState.Success(it)
                }
        }
    }

    var detailState =
        mutableStateOf<Task>(newTask(), policy = object : SnapshotMutationPolicy<Task> {
            override fun equivalent(a: Task, b: Task): Boolean {
                return false
            }
        })

    fun searchTask(taskId: Int) {
        Log.d("alvin", "searchTask")
        var newTask = Task(title = "", desc = "", isDone = false)
        viewModelScope.launch {
            repository.searchTask(taskId = taskId)
                .collect {
                    Log.d("alvin", "searchTask collect:${it}")
                    detailState.value = it ?: newTask
                }
        }
    }

    fun createTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertTask(task = task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTask(task = task)
        }
    }

    fun deleteTaskById(taskId: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            taskId?.let {
                repository.deleteTaskById(it)
            }

        }
    }

    fun deleteAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllTasks()
        }
    }

    fun updateDraft(task: Task) {
        viewModelScope.launch {
            detailState.value = task
        }

    }

    fun clearTask() {
        detailState.value = newTask()
    }

    fun newDraftTask() {
        detailState.value = newTask()
    }
}