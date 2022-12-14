package com.a.wanandroid.net

import com.a.wanandroid.net.bean.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    /**
     * 获取轮播图
     * http://www.wanandroid.com/banner/json
     */
    @GET("banner/json")
    suspend fun getBanners(): HttpResponse<List<Banner>>


    /**
     * 获取首页置顶文章列表
     * http://www.wanandroid.com/article/top/json
     */
    @GET("article/top/json")
    suspend fun getTopArticles(): HttpResponse<List<Article>>

    /**
     * 获取文章列表
     * http://www.wanandroid.com/article/list/0/json
     * @param pageNum
     */
    @GET("article/list/{page}/json")
    suspend fun getArticles(@Path("page") page: Int): HttpResponse<PageArticlesResponse>


    /**
     * 获取公众号列表
     * http://wanandroid.com/wxarticle/chapters/json
     */
    @GET("/wxarticle/chapters/json")
    suspend fun getWxChapters(): HttpResponse<List<WxChapterBean>>

    /**
     * 查看某个公众号历史数据
     * https://wanandroid.com/wxarticle/list/408/1/json
     * @param id 公众号 ID
     * @param page 公众号页码
     */
    @GET("/wxarticle/list/{id}/{page}/json")
    suspend fun getWxArticles(
        @Path("id") id: Int,
        @Path("page") page: Int
    ): HttpResponse<PageArticlesResponse>


    /**
     * 查看某个项目分类下的项目
     * https://www.wanandroid.com/project/list/1/json?cid=294
     * @param id 公众号 ID
     * @param page 公众号页码
     */
    @GET("/project/list/{page}/json")
    suspend fun getProjectArticles(
        @Path("page") page: Int,
        @Query("cid") cid: Int = 294
    ): HttpResponse<PageArticlesResponse>
    /**
     * 网址导航
     *  https://www.wanandroid.com/navi/json
     * @param id 公众号 ID
     * @param page 公众号页码
     */
    @GET("/navi/json")
    suspend fun getNavi( ): HttpResponse<List<NavBean>>


}