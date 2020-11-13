package com.newsuper.code.swipe


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.newsuper.code.R
import kotlinx.android.synthetic.main.activity_liquid_swipe.*

class LiquidSwipeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liquid_swipe)

        // viewpager.adapter = CustomFragmentPagerAdapter(supportFragmentManager)
        // viewpager.setCurrentItem(titleArray.count() * 10, false)
        /******************以下2种方案均可****************************/
        val liquidSwipeClipPathProviders = Array(titleArray.count()) {
            LiquidSwipeClipPathProvider()
        }

        viewpager.adapter = CustomPagerAdapter(this, liquidSwipeClipPathProviders)
        viewpager.setOnTouchListener { _, event ->
            val waveCenterY = event.y
            liquidSwipeClipPathProviders.map {
                it.waveCenterY = waveCenterY
            }
            false
        }
    }
}