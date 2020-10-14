package com.newsuper.code.gank.ui

import android.view.View
import com.newsuper.code.R
import com.newsuper.code.gank.data.Gank
import com.newsuper.code.gank.ui.GankWebActivity
import com.newsuper.code.gank.util.getDateString

import kotlinx.android.synthetic.main.recycler_item_gank_data.view.*

class GankPagedAdapter(retryCallback: () -> Unit) :
    BaseGankPagedAdapter(R.layout.recycler_item_gank_data, retryCallback) {

    override fun render(itemView: View, data: Gank?) {
        itemView.tv_desc.text = data?.desc
        itemView.tv_who.text = data?.who
        itemView.tv_date.text = data?.publishedAt?.getDateString()
        itemView.item_wrapper.tag = data
        itemView.item_wrapper.setOnClickListener(onClickListener)
    }

    private val onClickListener = View.OnClickListener {
        val gank = it.tag as Gank?
        if (gank != null)
            GankWebActivity.start(it.context, gank.url)
    }
}