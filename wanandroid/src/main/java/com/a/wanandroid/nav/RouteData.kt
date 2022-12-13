package com.a.wanandroid.nav

import androidx.compose.ui.graphics.vector.ImageVector
import com.a.wanandroid.R

object RouteData {

    const val splash = "Splash"
    const val main = "Main"
    const val main_index = "Main/Index"
    const val main_wxarticle = "Main/Wxarticle"
    const val main_project = "Main/Project"
    const val main_website = "Main/WebSite"
    const val article_detail = "ArticleDetail?title={title}?link={link}"
    const val article_detail_format = "ArticleDetail?title=%s?link=%s"

    val mainBottomItems = listOf(
        BottomItem(main_website, R.mipmap.icon_website,"网址导航"),
        BottomItem(main_index, R.mipmap.ic_recommand,"首页"),
        BottomItem(main_wxarticle, R.mipmap.ic_wxarticle,"公众号"),
        BottomItem(main_project, R.mipmap.ic_project_code,"项目"),

    )
}

data class BottomItem(
    val route: String,
    val resImg: Int,
    val text: String
)