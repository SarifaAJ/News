package com.example.tamasya.repo

import com.example.tamasya.base.BaseHelper
import com.example.tamasya.base.BaseResponse
import com.example.tamasya.model.DataItem
import com.example.tamasya.model.NewsCategory
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