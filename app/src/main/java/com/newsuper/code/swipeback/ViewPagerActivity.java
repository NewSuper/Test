package com.newsuper.code.swipeback;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.newsuper.code.R;

public class ViewPagerActivity extends BaseSwipeActivity {
    private ViewPager mViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_viewpager;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(adapter);
    }

    private FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            return TestFragment.newInstance(String.valueOf(position));
        }

        @Override
        public int getCount() {
            return 3;
        }
    };
}