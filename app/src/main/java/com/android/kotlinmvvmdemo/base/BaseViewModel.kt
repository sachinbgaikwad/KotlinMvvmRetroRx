package com.android.kotlinmvvmdemo.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.android.kotlinmvvmdemo.data.AppDataManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Sachin G. on 6/1/19.
 */
open class BaseViewModel : ViewModel() {

    private val subscriptions = CompositeDisposable()
    internal var errorLiveData: MutableLiveData<String> = MutableLiveData()

    fun subscribe(disposable: Disposable?): Disposable? {
        disposable?.let { subscriptions.add(it) }
        return disposable
    }

    override fun onCleared() {
        subscriptions.clear()
        super.onCleared()
    }

    var appDataManager: AppDataManager? = AppDataManager.getInstance()
}