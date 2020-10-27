package com.newsuper.code.dianzan.pop

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.newsuper.code.R
import kotlinx.android.synthetic.main.activity_list.*


class ListActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val data = mutableListOf<String>()
        for (i in 1..6){
            data.add("$i")
        }

        val adapter = RecyclerAdapter(R.layout.adapter_friend_comment_item)
        mRv.adapter = adapter
        adapter.setNewData(data)
        mRv.layoutManager = LinearLayoutManager(this)

//        val adapter = ListAdapter(this)
//        mRv.adapter = adapter

        val unDoPop = WPopup.Builder(this)
                .setData(listOf(WPopupModel("撤回")))
                .setClickView(mRv)
                .setOnItemClickListener(object : WPopup.Builder.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        Toast.makeText(this@ListActivity, "撤回", Toast.LENGTH_SHORT).show()
                    }

                })
                .create()

//        mRv.setOnItemLongClickListener { parent, view, position, id ->
//            unDoPop.showAtFingerLocation(WPopupDirection.RIGHT_BOTTOM)
//            false
//        }

        val friendCircleData = mutableListOf(WPopupModel("点赞", R.mipmap.asy, "取消", R.mipmap.asy), WPopupModel("评论", R.mipmap.asy))
        val friendCirclePop = WPopup.Builder(this)
                .setData(friendCircleData)
                .setAnim(WPopupAnim.ANIM_FRIEND_CIRCLE)
                .setIconDirection(WPopupDirection.LEFT)
                .setEnableChangeAnim(true)
                .setPopupOrientation(WPopup.Builder.HORIZONTAL)
                .setOnItemClickListener(object : WPopup.Builder.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        Toast.makeText(view.context, friendCircleData[position].text, Toast.LENGTH_LONG).show()
                    }
                })
                .create()


        adapter.setOnItemChildClickListener { adapter, view, position ->
            friendCirclePop.showAtDirectionByListView(view, WPopupDirection.LEFT, position)
        }

        adapter.setOnItemChildLongClickListener { adapter, view, position ->
            unDoPop.showAtFingerLocation(WPopupDirection.TOP)
            false
        }
    }
}
