package com.a.todolist.ui.screen.detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.a.todolist.nav.Nav
import com.a.todolist.ui.viewmodels.MainViewModel
import com.boycoder.todo.app.data.entity.Task

@Composable
fun DetailScreen(
    vm: MainViewModel,
    taskId: Int? = null
) {
    BackHandler() {
        Nav.goHome()
    }
    val isEdit = taskId != null && taskId > 0
    LaunchedEffect(key1 = "init", block = {
        if(isEdit) {
            vm.searchTask(taskId ?: 0)
        }else{
            vm.newDraftTask()
        }
    })
    DetailContent(
        isEdit = isEdit,
        listener = DetailEventListenerImpl(vm),
        provider = { vm.detailState.value }
    )
    DisposableEffect(key1 = "", effect = {
        onDispose {
            Log.d("alvin", "onDispose")
        }
    })
}

class DetailEventListenerImpl(private val vm: MainViewModel) : DetailEventListener {
    override fun onCancelClick() {
        Nav.goHome()
    }

    override fun onCreateClick(task: Task) {
        vm.createTask(task)
        Nav.goHome()
    }

    override fun onDelClick(task: Task) {
        vm.deleteTaskById(task.id)
        Nav.goHome()
    }

    override fun onUpdateClick(task: Task) {
        vm.updateTask(task)
        Nav.goHome()
    }

    override fun onContentChange(task: Task) {
        vm.updateDraft(task)
    }

}

interface DetailEventListener {
    fun onCancelClick()
    fun onCreateClick(task: Task)
    fun onDelClick(task: Task)
    fun onUpdateClick(task: Task)
    fun onContentChange(task: Task)
}

//
@SuppressLint("UnrememberedMutableState")
@Composable
fun DetailContent(
    isEdit: Boolean,
    listener: DetailEventListener? = null,
    provider: () -> Task
) {
    var task = provider.invoke()

    Log.d("alvin", "DetailContent 111 ${task}")
    if (isEdit && task.id <= 0) return

    Column {
        Log.d("alvin", "Column 111 ")
        if (!isEdit) {
            TaskCreateAppBar(onBackClick = {
                listener?.onCancelClick()
            }, onCheckClick = {
                listener?.onCreateClick(task)
            })
        } else {
            TaskEditAppBar(
                titleString = "title",
                onBackClick = { listener?.onCancelClick() },
                onDelClick = {
                    listener?.onDelClick(task)
                },
                onCheckClick = {
                    listener?.onUpdateClick(task)
                })
        }
        Row(
            modifier = Modifier
                .background(Color.White)
                .padding(10.dp)
        ) {
            Log.d("alvin", "标题栏 111 ")
            TextField(
                modifier = Modifier.weight(1.0f),
                colors = TextFieldDefaults.colors(),
                value = task.title,
                onValueChange = {
                    Log.d("alvin", "onValueChange title:${it}")
                    listener?.onContentChange(task.apply { title = it })
                },
                label = { Text(text = "请输入标题") },
                singleLine = true
            )
            Checkbox(checked = task.isDone, onCheckedChange = {
                Log.d("alvin", "onValueChange isDone:${it}")
                listener?.onContentChange(task.apply { isDone = it })
            })
        }
        TextField(
            modifier = Modifier
                .background(Color.White)
                .padding(10.dp)
                .background(Color.White)
                .defaultMinSize(minHeight = 150.dp)
                .fillMaxWidth(),
            value = task.desc,
            onValueChange = {
                Log.d("alvin", "onValueChange desc:${it}")
                listener?.onContentChange(task.apply { desc = it })
            },
            label = { Text(text = "请输入内容") },
        )
    }
}
