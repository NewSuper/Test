package com.newsuper.code.xuanfu

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.newsuper.code.R
import kotlinx.android.synthetic.main.activity_xuanfu_window.*


class XuanFuWindowAty : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xuanfu_window)

       // Log.e("TAG", "width--->${DisplayUtil.getScreenWidth(this@MainActivity)}")
       // Log.e("TAG", "height--->${DisplayUtil.getScreenHeight(this@MainActivity)}")

        amTvGo.setOnClickListener {
            startActivity(Intent(this, ArticleListActivity::class.java))
//            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}