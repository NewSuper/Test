package com.newsuper.code.gank.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.newsuper.code.R
import com.newsuper.code.databinding.ActivitySearchBinding
import com.newsuper.code.gank.data.SearchViewModel
import com.newsuper.code.gank.util.NetworkState
import com.newsuper.code.gank.util.hideKeyboard
import com.newsuper.code.gank.util.obtainViewModel


import kotlinx.android.synthetic.main.activity_search.*


class GankSearchActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewBinding: ActivitySearchBinding
    private lateinit var viewModel: SearchViewModel

    companion object {

        @JvmStatic
        fun start(context: Context) {
            context.startActivity(Intent(context, GankSearchActivity::class.java))
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        viewModel = obtainViewModel(SearchViewModel::class.java)
        viewBinding.viewModel = viewModel

        val adapter = GankPagedAdapter{
            viewModel.retry()
        }
        viewBinding.rvSearchResult.layoutManager = LinearLayoutManager(this)
        viewBinding.rvSearchResult.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        viewBinding.rvSearchResult.adapter = adapter

        viewBinding.btnClose.setOnClickListener(this)
        viewBinding.btnSearch.setOnClickListener(this)

        viewBinding.edtSearchBox.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                refresh()
                return@setOnEditorActionListener true
            }
            false
        }

        viewModel.gankList.observe(this, Observer {
            adapter.submitList(it)
        })

        viewBinding.viewModel?.refreshState?.observe(this, Observer {
            viewBinding.rvSearchResult.post {
                viewBinding.swipeRefreshLayout.isRefreshing = it == NetworkState.LOADING
            }
        })

        viewBinding.viewModel?.networkState?.observe(this, Observer {
            adapter.setNetworkState(it)
        })
    }


    private fun refresh() {
        val text = edt_search_box.text.toString().trim()
        if (TextUtils.isEmpty(text)) {
            Toast.makeText(this, R.string.search_content_empty, Toast.LENGTH_SHORT).show()
            return
        }
        hideKeyboard()
        viewModel.search(text)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_close -> {
                finish()
            }
            R.id.btn_search -> {
                refresh()
            }
        }
    }


}
