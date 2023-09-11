package com.example.news.network

import com.example.news.base.BaseResponse
import com.example.news.model.DataItem
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceServer {
    // news
    @GET("news")
    fun getNews(): Observable<BaseResponse<List<DataItem>>>

    // news detail
    @GET("news/{id}")
    fun getDetail(@Path("id") id: String): Observable<BaseResponse<DataItem>>
}
