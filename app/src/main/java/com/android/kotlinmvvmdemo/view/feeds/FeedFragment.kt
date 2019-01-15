package com .android.kotlinmvvmdemo.view.feeds

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.kotlinmvvmdemo.R
import com.android.kotlinmvvmdemo.base.BaseFragment
import com.android.kotlinmvvmdemo.data.model.FeedResponse
import com.android.kotlinmvvmdemo.data.model.Row
import com.android.kotlinmvvmdemo.util.getViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_feeds.*
import com.android.kotlinmvvmdemo.util.GridSpacingItemDecoration
import com.android.kotlinmvvmdemo.util.NoConnectivityException
import retrofit2.Retrofit


/**
 * Created by Sachin G. on 6/1/19.
 */
class FeedFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

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
        return inflater.inflate(R.layout.fragment_feeds, container, false)
    }

    override fun onStart() {
        super.onStart()
        init()
    }

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
        fetchData()
    }

    private fun showError() {
        Toast.makeText(context, "An error occurred :(", Toast.LENGTH_SHORT).show()
    }

    private fun showError(errorMessage: String?) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun showFeeds(it: FeedResponse?) {
        activity?.title = it?.title
//        if (it?.rows ?: != null)
//            if (!it.rows.isEmpty()) {
//                mItems.clear()
//                for (row in it?.rows!!) {
//                    if (row.title == null && row.imageHref == null && row.description == null) {
//                    } else {
//                        mItems.add(row)
//                    }
//                }
////                mItems.addAll(it.rows)
//                mFeedsAdapter.notifyDataSetChanged()
//            }
        mItems.addAll(it!!.rows)
        mFeedsAdapter.notifyDataSetChanged()
    }

    private fun fetchData() {
        mSwipeRefreshLayout.isRefreshing = true
        subscribe(
            feedViewModel.getFeeds()?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())?.subscribe({
                mSwipeRefreshLayout.isRefreshing = false
                Log.d("FeedFragment", it.toString())
                showFeeds(it)
            }, {
                showError(it.message)
                mSwipeRefreshLayout.isRefreshing = false
                Log.d("FeedFragment", it.toString())
            })
        )
    }

    override fun onRefresh() {
        fetchData()
    }
}