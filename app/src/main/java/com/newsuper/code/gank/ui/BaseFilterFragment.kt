package com.newsuper.code.gank.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.newsuper.code.databinding.FragmentGankFilterBinding
import com.newsuper.code.gank.util.NetworkState

abstract class BaseFilterFragment : Fragment(){
    lateinit var viewBinding:FragmentGankFilterBinding
    abstract fun getLayoutManager():RecyclerView.LayoutManager
    abstract fun getItemDecoration():RecyclerView.ItemDecoration?
    abstract fun getAdapter():BaseGankPagedAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewBinding = FragmentGankFilterBinding.inflate(inflater,container,false).apply {
            viewModel = (activity as GankActivity).obtainGankFilterViewModel()
        }
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewBinding.lifecycleOwner = viewLifecycleOwner
        viewBinding.rvGankFilter.layoutManager =getLayoutManager()
        getItemDecoration()?.also {
            viewBinding.rvGankFilter.addItemDecoration(it)
        }
        val adapter = getAdapter()
        viewBinding.rvGankFilter.adapter = adapter
        viewBinding.viewModel?.gankList?.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        viewBinding.viewModel?.refreshState?.observe(viewLifecycleOwner, Observer {
            viewBinding.rvGankFilter.post {
                viewBinding.swipeRefreshLayout.isRefreshing = it == NetworkState.LOADING
            }
        })
        viewBinding.viewModel?.networkState?.observe(viewLifecycleOwner, Observer {
            adapter.setNetworkState(it)
        })
    }

}