package com.a.todolist.ui.screen.home

import android.icu.text.CaseMap.Title
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.a.todolist.R
import com.a.todolist.consts.RequestState
import com.a.todolist.nav.Nav
import com.a.todolist.ui.theme.*
import com.a.todolist.ui.viewmodels.MainViewModel
import com.boycoder.todo.app.data.entity.Task

@Composable
fun HomeScreen(vm: MainViewModel) {

    val tasks by vm.allTasks.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Log.d("alvin", "Column ")
            HomeAppBar(onMoreClick = {})
            if (tasks is RequestState.Success) {
                val data = (tasks as RequestState.Success<List<Task>>).data
                if (data.isNotEmpty()) {
                    LazyColumn(content = {
                        items(data.size) { i: Int ->
                            TaskItem(data[i])
                        }
                    })

                } else {
                    HomeEmpty()
                }

            } else {
                HomeEmpty()
            }
        }
        FloatAdd(
            modifier = Modifier
                .padding(12.dp)
                .align(alignment = Alignment.BottomEnd),
            onClick = {
//                vm.clearTask()
                Nav.goDetail(-1)
            }

        )
    }

}


@Composable
fun HomeAppBar(onMoreClick: (() -> Unit)?) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Lavender5)
            .padding(horizontal = 12.dp)
    ) {
        Text(
            text = "任务标题",
            color = MaterialTheme.colors.topAppBarContent,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Icon(
            modifier = Modifier.clickable { onMoreClick?.invoke() },
            imageVector = Icons.Filled.Menu,
            contentDescription = stringResource(id = R.string.list_screen_title),
            tint = MaterialTheme.colors.topAppBarContent
        )
    }
}

@Composable
fun TaskItem(task: Task) {
    var checkValue by remember { mutableStateOf(task.isDone) }
    Row(
        modifier = Modifier
            .clickable { Nav.goDetail(task.id) }
            .height(80.dp)
            .background(Color.White)
            .padding(start = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = task.title,
                color = MaterialTheme.colors.taskItemText,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
            Text(text = task.desc)
        }
        Checkbox(
            checked = checkValue, onCheckedChange = { checkValue = it })
    }
}

@Composable
fun HomeEmpty() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.ic_baseline_auto_awesome_24),
            contentDescription = stringResource(id = R.string.sad_face_icon),
            tint = Neutral5
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "暂时没有任务", color = Neutral5)
    }
}

@Composable
@Preview
fun HomeEmptyPreview() {
    HomeEmpty()
}

@Composable
fun FloatAdd(modifier: Modifier = Modifier, onClick: (() -> Unit)?) {
    Box(
        modifier = modifier
    ) {
        FloatingActionButton(
            onClick = { onClick?.invoke() },
            backgroundColor = Teal200
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(id = R.string.add_button),
                tint = Color.White
            )

        }
    }
}

