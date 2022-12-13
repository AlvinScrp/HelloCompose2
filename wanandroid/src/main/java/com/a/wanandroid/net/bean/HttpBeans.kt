package com.a.wanandroid.net.bean


data class HttpResponse<T>(
    var errorCode: Int = 0,
    var errorMsg: String = "",
    val data: T?
)

//轮播图
data class Banner(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)

data class PageArticlesResponse(
    val curPage: Int,
    var datas: List<Article>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)

data class Article(
    val apkLink: String?,
    val audit: Int?,
    val author: String?,
    val chapterId: Int?,
    val chapterName: String?,
    var collect: Boolean?,
    val courseId: Int?,
    val desc: String?,
    val envelopePic: String?,
    val fresh: Boolean?,
    val id: Int?,
    val link: String?,
    val niceDate: String?,
    val niceShareDate: String?,
    val origin: String?,
    val prefix: String?,
    val projectLink: String?,
    val publishTime: Long?,
    val shareDate: String?,
    val shareUser: String?,
    val superChapterId: Int?,
    val superChapterName: String?,
    val tags: MutableList<Tag>?,
    val title: String?,
    val type: Int?,
    val userId: Int?,
    val visible: Int?,
    val zan: Int?,
    var top: String?
)

data class Tag(
    val name: String,
    val url: String
)


// 公众号列表实体
data class WxChapterBean(
    val children: List<String>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)

data class NavBean(val cid: Int?, val name: String?, val articles: List<Article>?)



