package com.newsuper.code.swipe

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

@SuppressLint("WrongConstant")
class CustomFragmentPagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return DummyFragment.newInstance(
                backgroundColorArray[(position % titleArray.count())],
                resourceArray[(position % titleArray.count())],
                titleArray[(position % titleArray.count())]
        )
    }

    override fun getCount(): Int {
        return titleArray.count() * 20
    }
}