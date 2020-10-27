package com.newsuper.code.dianzan.pop

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.newsuper.code.R

class RecyclerAdapter(layoutResId: Int) : BaseQuickAdapter<String, BaseViewHolder>(layoutResId) {
    override fun convert(helper: BaseViewHolder?, item: String?) {
        helper!!.addOnLongClickListener(R.id.mTvItem)
        helper.addOnClickListener(R.id.btn)
    }
}