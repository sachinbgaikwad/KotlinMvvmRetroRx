package com.android.kotlinmvvmdemo.data.model

/**
 * Created by Sachin G. on 6/1/19.
 */
data class FeedResponse(
    val rows: MutableList<Row>,
    val title: String?
)

data class Row(
    val description: String?,
    val imageHref: String?,
    val title: String?
)