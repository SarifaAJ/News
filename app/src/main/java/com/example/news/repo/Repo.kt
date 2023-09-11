package com.example.news.repo

import com.example.news.base.BaseHelper
import com.example.news.base.BaseResponse
import com.example.news.model.DataItem
import io.reactivex.Observable

class Repo: BaseHelper() {

    // news
    fun getNews(): Observable<BaseResponse<List<DataItem>>> {
        return  ApiServiceServer.getNews()
    }

    // news detail
    fun getDetail(id: String): Observable<BaseResponse<DataItem>> {
        return  ApiServiceServer.getDetail(id)
    }
}