package com.android.kotlinmvvmdemo.view.feeds

import com.android.kotlinmvvmdemo.base.BaseViewModel
import com.android.kotlinmvvmdemo.data.model.FeedResponse
import io.reactivex.Observable

/**
 * Created by Sachin G. on 6/1/19.
 */
class FeedViewModel : BaseViewModel() {

    fun getFeeds(): Observable<FeedResponse>? {
        return appDataManager?.getFeeds()
    }
}