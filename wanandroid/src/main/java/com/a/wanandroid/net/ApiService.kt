package com.a.wanandroid.net

import com.a.wanandroid.net.bean.Banner
import com.a.wanandroid.net.bean.HttpResponse
import retrofit2.http.GET

interface ApiService {
    /**
     * 获取轮播图
     * http://www.wanandroid.com/banner/json
     */
    @GET("banner/json")
    suspend fun getBanners(): HttpResponse<List<Banner>>
}