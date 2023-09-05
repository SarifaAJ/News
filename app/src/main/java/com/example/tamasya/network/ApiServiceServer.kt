package com.example.tamasya.network

import com.example.tamasya.base.BaseResponse
import com.example.tamasya.model.DataItem
import com.example.tamasya.model.NewsCategory
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
