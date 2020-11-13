package com.newsuper.code.tabscroll;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;
import com.newsuper.code.R;

public class TabScrollActivity extends AppCompatActivity implements ObservableScrollView.ScrollViewListener {
    private static final String TAG = "MainActivity";
    private FrameLayout wrapperFl;
    private TabLayout tabLayout;
    private ObservableScrollView scrollView;
    private LinearLayout containerLl;
    private boolean firstAlreadyInflated = true;
    private ViewGroup firstFloorVg;
    private ViewGroup secondFloorVg;
    private ViewGroup thirdFloorVg;
    private ViewGroup fourthFloorVg;
    private int secondFloorVgPositionDistance;//第二层滑动至顶部的距离
    private int thirdFloorVgPositionDistance;
    private int fourthFloorVgPositionDistance;
    private int currentPosition = 0;
    private boolean tabInterceptTouchEventTag = true;//标志位，用来区分是点击了tab还是手动滑动scrollview

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabscroll);
        initViews();
        initListeners();
    }

    private void initViews() {
        wrapperFl = (FrameLayout) findViewById(R.id.wrapperFl);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        scrollView = (ObservableScrollView) findViewById(R.id.scrollView);
        containerLl = (LinearLayout) findViewById(R.id.containerLl);
        for (int i = 0; i < 4; i++) {
            tabLayout.addTab(tabLayout.newTab().setText("tab" + (i + 1)));
        }

        firstFloorVg = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.item_floor_first, null);
        secondFloorVg = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.item_floor_second, null);
        thirdFloorVg = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.item_floor_third, null);
        fourthFloorVg = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.item_floor_fourth, null);

        containerLl.addView(firstFloorVg);
        containerLl.addView(secondFloorVg);
        containerLl.addView(thirdFloorVg);
        containerLl.addView(fourthFloorVg);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (firstAlreadyInflated) {//获取各层离screen顶部的位置以及计算滑动值相应顶部所需要的距离
            firstAlreadyInflated = false;
            int[] firstFloorVgPosition = new int[2];
            int[] secondFloorVgPosition = new int[2];
            int[] thirdFloorVgPosition = new int[2];
            int[] fourthFloorVgPosition = new int[2];
            firstFloorVg.getLocationOnScreen(firstFloorVgPosition);
            secondFloorVg.getLocationOnScreen(secondFloorVgPosition);
            thirdFloorVg.getLocationOnScreen(thirdFloorVgPosition);
            fourthFloorVg.getLocationOnScreen(fourthFloorVgPosition);
            int firstFloorVgPositionAnchor = firstFloorVgPosition[1];
            int secondFloorVgPositionAnchor = secondFloorVgPosition[1];
            int thirdFloorVgPositionAnchor = thirdFloorVgPosition[1];
            int fourthFloorVgPositionAnchor = fourthFloorVgPosition[1];

            Log.d(TAG, "第一层距离屏幕的距离是：" + firstFloorVgPosition[1]);
            Log.d(TAG, "第二层距离屏幕的距离是：" + secondFloorVgPosition[1]);
            Log.d(TAG, "第三层距离屏幕的距离是：" + thirdFloorVgPosition[1]);
            Log.d(TAG, "第四层距离屏幕的距离是：" + fourthFloorVgPosition[1]);

            secondFloorVgPositionDistance = secondFloorVgPositionAnchor - firstFloorVgPositionAnchor;
            thirdFloorVgPositionDistance = thirdFloorVgPositionAnchor - firstFloorVgPositionAnchor;
            fourthFloorVgPositionDistance = fourthFloorVgPositionAnchor - firstFloorVgPositionAnchor;
        }
    }

    private void initListeners() {
        wrapperFl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG,"wrapperFl onTouch");
                tabInterceptTouchEventTag = true;//让tab来处理滑动
                return false;
            }
        });

//        a. tabInterceptTouchEventTag 作为标志位是为了防止因scrollview触发了tab Selected从而再次引起scrollview滑动导致的滑动不流畅。
//
//        b. wrapperFl的存在则是要去给 tabInterceptTouchEventTag 赋值，因为TabLayout的Touch、Click、Focus等事件被消化掉了，无法在这些事件中监听到对应的值的变化，所以通过wrapperFl来进行Touch事件的监听。
//
//        c. ObservableScrollView 是继承自Scrollview，新增和改变了其中的以下方法：

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentPosition = tab.getPosition();
                if(!tabInterceptTouchEventTag){//手动滑动页面时则不再次处理滑动
                    return;
                }
                scrollView.computeScroll();
                switch (currentPosition) {
                    case 0:
                        scrollView.smoothScrollTo(0, 0);
                        break;
                    case 1:
                        scrollView.smoothScrollTo(0, secondFloorVgPositionDistance);
                        break;
                    case 2:
                        scrollView.smoothScrollTo(0, thirdFloorVgPositionDistance);
                        break;
                    case 3:
                        scrollView.smoothScrollTo(0, fourthFloorVgPositionDistance);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        scrollView.setScrollViewListener(this);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "scrollView onTouch");
                tabInterceptTouchEventTag = false;//让scrollview处理滑动
                return false;
            }
        });
    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (tabInterceptTouchEventTag) {//让tab来处理滑动
            return;
        }
        Log.d(TAG, "当前scrollView的位置——>" + y);
        if (y < secondFloorVgPositionDistance) {
            if (currentPosition != 0) {
                scrollView.computeScroll();
                tabLayout.getTabAt(0).select();
            }
        } else if (y < thirdFloorVgPositionDistance) {
            if (currentPosition != 1) {
                scrollView.computeScroll();
                tabLayout.getTabAt(1).select();
            }
        } else if (y < fourthFloorVgPositionDistance) {
            if (currentPosition != 2) {
                scrollView.computeScroll();
                tabLayout.getTabAt(2).select();
            }
        } else {
            if (currentPosition != 3) {
                scrollView.computeScroll();
                tabLayout.getTabAt(3).select();
            }
        }
    }
}
