package com.newsuper.code.gank.ui


import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

// 福利
class WelfareFragment : BaseFilterFragment() {
    override fun getLayoutManager(): RecyclerView.LayoutManager {
        return GridLayoutManager(activity, 2)
    }

    override fun getItemDecoration(): RecyclerView.ItemDecoration? {
        return null
    }

    override fun getAdapter(): BaseGankPagedAdapter {
        return WelfarePagedAdapter {
            viewBinding.viewModel?.retry()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = WelfareFragment()
    }
}
