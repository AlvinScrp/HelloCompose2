package com.a.wanandroid.net.bean


data class HttpResponse<T>(
    var errorCode: Int = 0,
    var errorMsg: String = "",
    val data: T
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