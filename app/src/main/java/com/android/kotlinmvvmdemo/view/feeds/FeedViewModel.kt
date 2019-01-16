package com.android.kotlinmvvmdemo.view.feeds

import android.arch.lifecycle.MutableLiveData
import com.android.kotlinmvvmdemo.base.BaseViewModel
import com.android.kotlinmvvmdemo.data.model.FeedResponse
import com.android.kotlinmvvmdemo.data.model.Row
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Sachin G. on 6/1/19.
 */
class FeedViewModel : BaseViewModel() {

    internal var responseLiveData: MutableLiveData<FeedResponse>? = MutableLiveData()

    fun getFeeds() {
        subscribe(
            appDataManager?.getFeeds()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    processData(it.rows)
                    responseLiveData?.postValue(it)
                }, {
                    errorLiveData.postValue(it.message)
                })
        )
    }

    fun processData(rows: List<Row>): List<Row> {
        return rows.filter {
            !it.title.isNullOrEmpty() &&
                    !it.description.isNullOrEmpty() &&
                    !it.imageHref.isNullOrEmpty()
        }
    }
}