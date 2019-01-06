package com.android.kotlinmvvmdemo.view.feeds

import com.android.kotlinmvvmdemo.data.model.FeedResponse
import com.android.kotlinmvvmdemo.data.model.Row
import io.reactivex.Observable
import io.reactivex.Observer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

/**
 * Created by Sachin G. on 6/1/19.
 */
class FeedViewModelTest {

    private var feedViewModel: FeedViewModel? = null

    @Before
    fun setUp() {
        feedViewModel = Mockito.mock(FeedViewModel::class.java)
    }

    @Test
    fun getFeeds() {
        Mockito.`when`(feedViewModel!!.getFeeds()).then {
            Observable.just(FeedResponse(emptyList(), "Kotlin Mockito Test"))
        }
        feedViewModel!!.getFeeds().test().assertValue {
            it.title.isEmpty()
            it.rows.isEmpty()
            it.title == "Kotlin Mockito Test"
        }
    }

    @After
    fun tearDown() {
        feedViewModel = null
    }
}