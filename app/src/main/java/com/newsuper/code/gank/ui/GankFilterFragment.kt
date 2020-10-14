package com.newsuper.code.gank.ui


import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// 按标签过滤
class GankFilterFragment : BaseFilterFragment() {
    override fun getLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(activity)
    }

    override fun getItemDecoration(): RecyclerView.ItemDecoration? {
        return DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
    }

    override fun getAdapter(): BaseGankPagedAdapter {
        return GankPagedAdapter{
            viewBinding.viewModel?.retry()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = GankFilterFragment()
    }
}
