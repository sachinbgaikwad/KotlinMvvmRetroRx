package com.android.kotlinmvvmdemo.view.feeds

import com.android.kotlinmvvmdemo.data.model.FeedResponse
import com.android.kotlinmvvmdemo.data.model.Row
import io.reactivex.Observable
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.util.*

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
    fun processData() {
        Mockito.`when`(feedViewModel?.processData(emptyList())).then {
            return@then Collections.emptyList<Row>()
        }
        Assert.assertNotNull(feedViewModel?.processData(emptyList()))
    }

    @After
    fun tearDown() {
        feedViewModel = null
    }
}