package com.a.wanandroid.screen.main.base

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.a.wanandroid.net.bean.Article

@Composable
fun ArticleItem(
    article: Article,
    isTop: Boolean = false,
    showChapter: Boolean = true
) {

    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row() {
                Text(
                    modifier = Modifier.padding(end = 12.dp),
                    text = article.userName(),
                    color = Color(0xFF808394),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(text = article.niceDate.orEmpty(), color = Color(0xFF81848A), fontSize = 12.sp)
            }

            Box(modifier = Modifier.padding(vertical = 11.dp)) {
                Text(
                    text = article.title.orEmpty(),
                    color = Color(0xFF000000),
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            if(showChapter) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = article.chapterLabel(),
                        color = Color(0xFF81848A),
                        fontSize = 12.sp
                    )
                }
            }

        }
        Divider(
            modifier = Modifier
                .padding(start = 12.dp)
                .height(0.5.dp),
            color = Color(0xFFEEEEEE)
        )
    }

}

fun Article.userName(): String {
    val userName = when {
        this.author.isNullOrEmpty().not() -> author!!
        this.shareUser.isNullOrEmpty().not() -> shareUser!!
        else -> ""
    }
    return userName
}

fun Article.chapterLabel(): String {
    return "${this.superChapterName.orEmpty()} / ${this.chapterName.orEmpty()}"
}

@Composable
@Preview
fun PreviewArticleItem() {

}


