package com.example.news.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news.base.BaseViewModel
import com.example.news.model.DataItem
import com.example.news.network.getErrorMessage
import com.example.news.repo.Repo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class DetailViewModel : ViewModel(), BaseViewModel<DataItem> {
    override val response = MutableLiveData<DataItem>()
    override val isLoading = MutableLiveData<Boolean>()
    override val errorMsg = MutableLiveData<String>()
    override var subscription: Disposable? = null

    private val repo = Repo()

    fun getDetail(id: String) {
        isLoading.value = true
        subscription?.dispose()

        subscription = repo.getDetail(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    isLoading.value = false
                    if (!result.error) {
                        response.value = result.data as DataItem
                    } else {
                        errorMsg.value = result.message
                    }
                },
                { error ->
                    isLoading.value = false
                    if (error is HttpException) {
                        val code = error.code()
                        errorMsg.value = getErrorMessage(code)
                    } else {
                        errorMsg.value = "Error: ${error.message}"
                    }
                }
            )
    }
}
