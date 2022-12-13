package com.a.wanandroid.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.a.wanandroid.component.WebViewContent

@Composable
fun ArticleDetailScreen(title: String = "", link: String, onBack: (() -> Unit)? = null) {

    Column() {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onBack?.invoke() }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "back",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
            }
            Text(
                modifier = Modifier.padding(end = 12.dp),
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }
        Divider(
            modifier = Modifier
                .padding(start = 12.dp)
                .height(0.5.dp),
            color = Color(0xFFEEEEEE)
        )
        WebViewContent(url = link)
    }

}