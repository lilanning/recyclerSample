package com.example.recyclersample.http

import android.icu.text.IDNA
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

//    /**
//     * 首页精选
//     */
//    @GET("v2/feed?")
//    fun getFirstHomeData(@Query("num") num:Int): Observable<HomeBean>
//
//    /**
//     * 根据 nextPageUrl 请求数据下一页数据
//     */
//    @GET
//    fun getMoreHomeData(@Url url: String): Observable<HomeBean>
//
//    /**
//     * 根据item id获取相关视频
//     */
//    @GET("v4/video/related?")
//    fun getRelatedData(@Query("id") id: Long): Observable<HomeBean.Issue>
//
//    /**
//     * 获取分类
//     */
//    @GET("v4/categories")
//    fun getCategory(): Observable<ArrayList<CategoryBean>>
//
//    /**
//     * 获取分类详情List
//     */
//    @GET("v4/categories/videoList?")
//    fun getCategoryDetailList(@Query("id") id: Long): Observable<HomeBean.Issue>
//
//    /**
//     * 获取更多的 Issue
//     */
//    @GET
//    fun getIssueData(@Url url: String): Observable<HomeBean.Issue>
//
//    /**
//     * 获取全部排行榜的Info（包括，title 和 Url）
//     */
//    @GET("v4/rankList")
//    fun getRankList(): Observable<TabInfoBean>
//
//    /**
//     * 获取搜索信息
//     */
//    @GET("v1/search?&num=10&start=10")
//    fun getSearchData(@Query("query") query :String) : Observable<HomeBean.Issue>
//
    /**
     * 热门搜索词
     */
    @GET("api/rand.music")
    fun getHotWord(@Query("sort") sort:String, @Query("format") format:String): Call<Data>


    @GET("api/rand.img1")
    fun getImage(@Query("sort") sort:String, @Query("format") format:String) : Call<Image>
//
//    /**
//     * 关注
//     */
//    @GET("v4/tabs/follow")
//    fun getFollowInfo(): Observable<HomeBean.Issue>
//
//    /**
//     * 作者信息
//     */
//    @GET("v4/pgcs/detail/tab?")
//    fun getAuthorInfo(@Query("id") id: Long): Observable<AuthorInfoBean>
}