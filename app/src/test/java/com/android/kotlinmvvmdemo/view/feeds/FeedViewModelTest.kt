package com.android.kotlinmvvmdemo.view.feeds

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
    fun getMutableLiveData() {
        Assert.assertNotNull(feedViewModel?.responseLiveData)
    }

    @After
    fun tearDown() {
        feedViewModel = null
    }
}