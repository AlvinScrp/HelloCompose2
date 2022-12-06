package com.a.todolist.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.a.todolist.R
import com.a.todolist.ui.theme.Lavender5
import com.a.todolist.ui.theme.topAppBarContent
import com.boycoder.todo.app.data.entity.Task


val appBarBackGroundModifier = Modifier
    .fillMaxWidth()
    .height(56.dp)
    .background(Lavender5)

@Composable
fun TaskEmptyAppBar() {
    Spacer(
        modifier = appBarBackGroundModifier
    )

}

@Composable
fun TaskCreateAppBar(onBackClick: (() -> Unit)?, onCheckClick: (() -> Unit)?) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = appBarBackGroundModifier
    ) {
        IconButton(onClick = { onBackClick?.invoke() }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.back_arrow),
                tint = MaterialTheme.colors.topAppBarContent
            )
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            text = "创建任务",
            color = MaterialTheme.colors.topAppBarContent,
        )

        IconButton(
            onClick = { onCheckClick?.invoke() }) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = stringResource(id = R.string.back_arrow),
                tint = MaterialTheme.colors.topAppBarContent
            )
        }
    }

}

@Composable
fun TaskEditAppBar(
    titleString: String,
    onBackClick: (() -> Unit)?,
    onDelClick: (() -> Unit)?,
    onCheckClick: (() -> Unit)?
) {

    ConstraintLayout(
        modifier = appBarBackGroundModifier
            .fillMaxSize()
            .padding(horizontal = 6.dp)
    ) {
        val (closeRef, titleRef, deleteRef, checkRef) = createRefs()
        Icon(
            modifier = Modifier
                .clickable { onBackClick?.invoke() }
                .padding(6.dp)
                .constrainAs(closeRef) {
                    start.linkTo(parent.start)
                    centerVerticallyTo(parent)
                },
            imageVector = Icons.Filled.Close,
            contentDescription = stringResource(id = R.string.back_arrow),
            tint = MaterialTheme.colors.topAppBarContent
        )
        Text(
            modifier = Modifier
                .padding(6.dp)
                .constrainAs(titleRef) {
                    start.linkTo(closeRef.end)
                    end.linkTo(deleteRef.start)
                    centerVerticallyTo(parent)
                    width = Dimension.fillToConstraints
                },
            text = titleString,
            color = MaterialTheme.colors.topAppBarContent,
        )

        Icon(
            modifier = Modifier
                .padding(6.dp)
                .clickable { onDelClick?.invoke() }
                .constrainAs(deleteRef) {
                    end.linkTo(checkRef.start)
                    start.linkTo(titleRef.end)
                    centerVerticallyTo(parent)
                },
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete_icon),
            tint = MaterialTheme.colors.topAppBarContent
        )

        Icon(
            modifier = Modifier
                .clickable { onCheckClick?.invoke() }
                .padding(6.dp)
                .constrainAs(checkRef) {
                    end.linkTo(parent.end)
                    centerVerticallyTo(parent)
                },
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.update_icon),
            tint = MaterialTheme.colors.topAppBarContent
        )
    }

}

@Composable
@Preview
private fun TaskCreateAppBarPreview() {

    Column {
        TaskCreateAppBar(null, null)
        Spacer(modifier = Modifier.height(20.dp))
        TaskEditAppBar("任务标题", null, null, null)
    }

}

