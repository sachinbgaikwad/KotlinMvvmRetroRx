package com.android.kotlinmvvmdemo.view.feeds

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.android.kotlinmvvmdemo.R
import com.android.kotlinmvvmdemo.base.BaseViewModel
import com.android.kotlinmvvmdemo.data.model.FeedResponse
import com.android.kotlinmvvmdemo.data.model.Row
import com.android.kotlinmvvmdemo.util.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Sachin G. on 6/1/19.
 */
class FeedViewModel(application: Application) : BaseViewModel(application) {

    var app: Application = application
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
                    if (Utils.isNetworkAvailable(app))
                        errorLiveData.postValue(it.message)
                    else
                        errorLiveData.postValue(app.getString(R.string.network_error))
                })
        )
    }

    private fun processData(rows: List<Row>): List<Row> {
        return rows.filter {
            !it.title.isNullOrEmpty() &&
                    !it.description.isNullOrEmpty() &&
                    !it.imageHref.isNullOrEmpty()
        }
    }
}