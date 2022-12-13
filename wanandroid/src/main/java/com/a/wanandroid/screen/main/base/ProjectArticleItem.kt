package com.a.wanandroid.screen.main.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.a.wanandroid.net.bean.Article
import com.google.gson.Gson

@Composable
fun ProjectArticleItem(
    article: Article
) {

    Column {
        Row(
            modifier = Modifier
                .padding(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .height(192.dp)
                    .weight(1.0f)
            ) {
                Row() {
                    Text(
                        modifier = Modifier.padding(end = 12.dp),
                        text = article.userName(),
                        color = Color(0xFF808394),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = article.niceDate.orEmpty(),
                        color = Color(0xFF81848A),
                        fontSize = 12.sp
                    )
                }


                Text(
                    modifier = Modifier.padding(top = 11.dp),
                    text = article.title.orEmpty(),
                    color = Color(0xFF000000),
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    modifier = Modifier.padding(top = 11.dp).weight(1.0f),
                    text = article.desc.orEmpty(),
                    color = Color(0xFF666666),
                    fontSize = 14.sp,
                    lineHeight = 19.sp,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    modifier = Modifier.padding(top = 11.dp).fillMaxWidth(),
                    text = article.chapterLabel(),
                    color = Color(0xFF81848A),
                    fontSize = 12.sp
                )
            }
            AsyncImage(
                modifier = Modifier
                    .padding(5.dp)
                    .width(108.dp)
                    .height(192.dp),
                model = article.envelopePic,
                contentDescription = "封面图",
                contentScale = ContentScale.Crop
            )
        }
        Divider(
            modifier = Modifier
                .padding(start = 12.dp)
                .height(0.5.dp),
            color = Color(0xFFEEEEEE)
        )
    }

}

@Preview
@Composable
fun PreviewProjectArticleItem() {
    var article = genArticleBean()
    ProjectArticleItem(article = article)

}

fun genArticleBean(): Article {
    var json = """ {
        "adminAdd": false,
        "apkLink": "",
        "audit": 1,
        "author": "stewForAni",
        "canEdit": false,
        "chapterId": 294,
        "chapterName": "完整项目",
        "collect": false,
        "courseId": 13,
        "desc": "我们经常会写一些包含大量模版代码的 Demo 应用，例如包含不同演示用例的列表、跳转逻辑、申请权限和假数据等等。 SamplePlugin 通过注解来提供各类组件并自动生成这些模版代码，以便查看",
        "descMd": "",
        "envelopePic": "https://www.wanandroid.com/blogimgs/6888e512-12cb-4443-8032-026d3cfb5d5d.png",
        "fresh": false,
        "host": "",
        "id": 24640,
        "isAdminAdd": false,
        "link": "https://www.wanandroid.com/blog/show/3436",
        "niceDate": "2022-10-16 20:07",
        "niceShareDate": "2022-10-16 20:07",
        "origin": "",
        "prefix": "",
        "projectLink": "https://github.com/stewForAni/KotlinBox-WanAndroid",
        "publishTime": 1665922032000,
        "realSuperChapterId": 293,
        "selfVisible": 0,
        "shareDate": 1665922032000,
        "shareUser": "",
        "superChapterId": 294,
        "superChapterName": "开源项目主Tab",
        "tags": [
          {
            "name": "项目",
            "url": "/project/list/1?cid=294"
          }
        ],
        "title": "WanAndroid-基础款",
        "type": 0,
        "userId": -1,
        "visible": 1,
        "zan": 0
      }"""
    val article = Gson().fromJson(json, Article::class.java)
    return article
}





