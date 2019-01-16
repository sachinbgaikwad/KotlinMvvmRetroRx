package com.android.kotlinmvvmdemo.view.feeds

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.kotlinmvvmdemo.base.BaseFragment
import com.android.kotlinmvvmdemo.data.model.FeedResponse
import com.android.kotlinmvvmdemo.data.model.Row
import com.android.kotlinmvvmdemo.util.*
import kotlinx.android.synthetic.main.fragment_feeds.*


/**
 * Created by Sachin G. on 6/1/19.
 */
class FeedFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener, OnItemClickListener {

    private lateinit var mFeedsAdapter: FeedsAdapter
    private val mItems: MutableList<Row> = mutableListOf()

    companion object {
        fun newInstance(): FeedFragment {
            return FeedFragment()
        }
    }

    private val feedViewModel: FeedViewModel by lazy {
        getViewModel { FeedViewModel() }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.android.kotlinmvvmdemo.R.layout.fragment_feeds, container, false)
    }

    override fun onStart() {
        super.onStart()
        init()
        initObserver()
    }

    private fun initObserver() {
        feedViewModel.responseLiveData?.observe(this, feedObserver())
    }

    private fun feedObserver(): Observer<FeedResponse> {
        return Observer {
            showFeeds(it)
        }
    }

    /**
     * Init UI related component
     * */
    private fun init() {
        mFeedsAdapter = FeedsAdapter(mItems)
        mSwipeRefreshLayout.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )

        mSwipeRefreshLayout.setOnRefreshListener(this)
        rvFeeds.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvFeeds.addItemDecoration(GridSpacingItemDecoration(1, 20, true, 0))
        rvFeeds.adapter = mFeedsAdapter
        rvFeeds.addOnItemClickListener(this)
        fetchData()
    }

    /**
     * Error messages are shown like empty list
     * */
    override fun showError(errorMessage: String?) {
        Toast.makeText(activity, errorMessage, Toast.LENGTH_SHORT).show()
        tvError.text = errorMessage
        if (mItems.isEmpty())
            tvError.visible()
        else
            tvError.gone()
    }

    /**
     * show data to UI
     * */
    private fun showFeeds(it: FeedResponse?) {
        mSwipeRefreshLayout.isRefreshing = false
        activity?.title = it?.title
        mItems.clear()
        mItems.addAll(it!!.rows)
        if (mItems.isEmpty())
            tvError.visible()
        else
            tvError.gone()
        mFeedsAdapter.notifyDataSetChanged()
    }

    private fun fetchData() {
        mSwipeRefreshLayout.isRefreshing = true
        feedViewModel.getFeeds()
    }

    /**
     * This method is called when PullToRefresh called
     * */
    override fun onRefresh() {
        fetchData()
    }

    /**
     * OnItemClick of RecyclerView
     * */
    override fun onItemClicked(position: Int, view: View) {
        showToast("Item selected : $position")
    }
}