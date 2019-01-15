package com.android.kotlinmvvmdemo.data.remote

import com.android.kotlinmvvmdemo.data.model.FeedResponse
import io.reactivex.Observable

/**
 * Created by Sachin G. on 6/1/19.
 */
interface ApiManager {

    fun getFeeds(): Observable<FeedResponse>?
}