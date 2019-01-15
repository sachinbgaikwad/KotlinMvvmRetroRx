package com.android.kotlinmvvmdemo.base

import android.support.v4.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Sachin G. on 6/1/19.
 */
open class BaseFragment : Fragment() {
    val subscriptions = CompositeDisposable()

    fun subscribe(disposable: Disposable?): Disposable? {
        disposable?.let { subscriptions.add(it) }
        return disposable
    }

    override fun onStop() {
        super.onStop()
        subscriptions.clear()
    }
}