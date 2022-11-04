package com.a.todolist.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.a.todolist.R
import com.a.todolist.ui.theme.Lavender5
import com.a.todolist.ui.theme.topAppBarBackground
import com.a.todolist.ui.theme.topAppBarContent


val appBarBackGroundModifier = Modifier
    .fillMaxWidth()
    .height(56.dp)
    .background(Lavender5)

@Composable
fun CreateTaskAppBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = appBarBackGroundModifier
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.back_arrow),
                tint = MaterialTheme.colors.topAppBarContent
            )
        }
        Text(
            text = "创建任务",
            color = MaterialTheme.colors.topAppBarContent,
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        IconButton(
            onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = stringResource(id = R.string.back_arrow),
                tint = MaterialTheme.colors.topAppBarContent
            )
        }
    }

}

@Composable
@Preview
private fun TaskCreateAppBarPreview() {
    CreateTaskAppBar()
}

