package com.android.kotlinmvvmdemo.data.remote.api

import com.android.kotlinmvvmdemo.data.model.FeedResponse
import retrofit2.http.GET
import io.reactivex.Observable

/**
 * Created by Sachin G. on 6/1/19.
 */
interface FeedApi {

    @GET("/s/2iodh4vg0eortkl/facts.json")
    fun getFeeds(): Observable<FeedResponse>?
}