package com.newsuper.code.flourish


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.newsuper.code.R
import kotlinx.android.synthetic.main.item_feed.view.*

class FeedAdapter : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    private val itemList: MutableList<FeedItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false)
        return FeedViewHolder(inflater.rootView)
    }

    fun addItem(feedItem: FeedItem) {
        itemList.add(feedItem)
        notifyItemInserted(itemList.indexOf(feedItem))
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val data = itemList[position]
        holder.itemView.run {
            profile.setImageDrawable(data.profile)
            title.text = data.title
            content.text = data.content
        }
    }

    override fun getItemCount() = itemList.size

    class FeedViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
