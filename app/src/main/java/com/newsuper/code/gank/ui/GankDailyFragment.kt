package com.newsuper.code.gank.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.newsuper.code.R
import com.newsuper.code.databinding.FragmentGankBinding


class GankDailyFragment : Fragment() {

    private lateinit var viewBinding: FragmentGankBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentGankBinding.inflate(inflater, container, false).apply {
            viewModel = (activity as GankActivity).obtainGankDailyViewModel()
        }
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewBinding.lifecycleOwner = viewLifecycleOwner

        // init recycler view
        viewBinding.rvGankDaily.layoutManager = LinearLayoutManager(activity)
        viewBinding.rvGankDaily.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        viewBinding.rvGankDaily.adapter = GankDailyAdapter()

        //add observer
        viewBinding.viewModel?.gankItemList?.observe(viewLifecycleOwner, Observer {
            (viewBinding.rvGankDaily.adapter as GankDailyAdapter).replaceItems(it)
        })
        viewBinding.viewModel?.netWorkError?.observe(viewLifecycleOwner, Observer {
            if (it)
                Toast.makeText(activity, R.string.network_error, Toast.LENGTH_SHORT).show()
        })

        //load data if empty
        if (viewBinding.viewModel?.gankItemList?.value.isNullOrEmpty())
            viewBinding.viewModel?.start()

    }

    companion object {
        const val TAG = "GankDailyFragment"
        @JvmStatic
        fun newInstance() = GankDailyFragment()
    }
}
