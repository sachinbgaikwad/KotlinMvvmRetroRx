package com.android.kotlinmvvmdemo.base

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast
import com.android.kotlinmvvmdemo.util.getViewModel

/**
 * Created by Sachin G. on 6/1/19.
 */
abstract class BaseFragment : Fragment() {

    private val baseViewModel: BaseViewModel by lazy {
        getViewModel { BaseViewModel() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseViewModel.errorLiveData.observe(this, Observer {
            showError(it)
        })
    }

    abstract fun showError(error: String?)

    fun showToast(error: String?) {
        Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()
    }

}