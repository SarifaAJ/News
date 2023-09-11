package com.example.news.helper

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.news.base.BaseViewModel

class ViewModelObserver<T>(
    private val owner: LifecycleOwner,
    viewModel: BaseViewModel<T>,
    private val loadingCallback: () -> Unit,
    private val removeLoadingCallback: () -> Unit,
    private val successCallback: (data: T) -> Unit,
    private val errorCallback: (message: String) -> Unit
) {

    private val loadingLiveData = viewModel.getLoading()
    private val responseLiveData = viewModel.getResponse()
    private val errorLiveData = viewModel.getError()

    private val loadingObserver = Observer<Boolean> { isLoading ->
        if (isLoading) {
            loadingCallback()
        }else {
            removeLoadingCallback()
        }
    }

    private val responseObserver = Observer<T> { responseData ->
        removeLoadingCallback()
        successCallback(responseData)
    }

    private val errorObserver = Observer<String> { errorMessage ->
        removeLoadingCallback()
        if (!errorMessage.isEmpty()) {
            errorCallback(errorMessage)
        }
    }

    fun observe() {
        loadingLiveData.observe(owner, loadingObserver)
        responseLiveData.observe(owner, responseObserver)
        errorLiveData.observe(owner, errorObserver)
    }

    fun removeObservers() {
        loadingLiveData.removeObserver(loadingObserver)
        responseLiveData.removeObserver(responseObserver)
        errorLiveData.removeObserver(errorObserver)
    }
}