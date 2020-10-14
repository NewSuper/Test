package com.newsuper.code.gank.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.newsuper.code.R
import kotlinx.android.synthetic.main.item_gallery.view.*

class GalleryAdapter(private val gankList: List<String>) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return gankList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = LayoutInflater.from(container.context).inflate(R.layout.item_gallery, container, false)
        val gank = gankList[position]
        Glide.with(container.context)
            .applyDefaultRequestOptions(RequestOptions().apply { centerCrop() })
            .load(gank)
            .into(itemView.photo_view)
        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        if (`object` is View) {
            container.removeView(`object`)
        }
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

}