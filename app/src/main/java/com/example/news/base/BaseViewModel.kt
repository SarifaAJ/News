package com.example.news.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.Disposable

interface BaseViewModel<T> {
    val response: MutableLiveData<T>
    val isLoading: MutableLiveData<Boolean>
    val errorMsg: MutableLiveData<String>
    var subscription: Disposable?

    fun getResponse(): LiveData<T> {
        return response
    }

    fun getLoading(): LiveData<Boolean> {
        return isLoading
    }

    fun getError(): LiveData<String> {
        return errorMsg
    }

    fun dispose() {
        subscription?.dispose()
    }
}
