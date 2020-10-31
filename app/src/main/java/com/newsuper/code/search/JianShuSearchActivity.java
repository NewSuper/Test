package com.newsuper.code.search;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;
import androidx.transition.TransitionSet;

import com.newsuper.code.R;

public class JianShuSearchActivity  extends AppCompatActivity {

   // @Bind(R.id.tv_search)
    TextView tvSearch;
   // @Bind(R.id.ll_search)
    LinearLayout mSearchLayout;
  //  @Bind(R.id.scrollView)
    ScrollView mScrollView;
    boolean isExpand = false;
  //  @Bind(R.id.iv_img)
    ImageView ivImg;
  //  @Bind(R.id.toolbar)
    Toolbar toolbar;
    private TransitionSet mSet;


//    实现这个效果， 只要关注几个点
//1.搜索栏伸展和收缩动画效果实现
//2.搜索栏伸展和收缩的时机
//3.顶部透明度的渐变
//    搜索栏伸展和收缩动画效果实现：
//
//    我们只要明确，使用系统为我们提供的 Transition 框架，就可以轻而易举的实现了。
//    首先要引入依赖 compile 'com.android.support:design:25.3.1'，要知道我们使用到的这部分 Transition 效果只是封装了属性动画的内容，是可以兼容到 5.0 之前的。
//
//    private void expand() {
//        //设置伸展状态时的布局
//        tvSearch.setText("搜索简书的内容和朋友");
//        RelativeLayout.LayoutParams LayoutParams = (RelativeLayout.LayoutParams) mSearchLayout.getLayoutParams();
//        LayoutParams.width = LayoutParams.MATCH_PARENT;
//        LayoutParams.setMargins(dip2px(10), dip2px(10), dip2px(10), dip2px(10));
//        mSearchLayout.setLayoutParams(LayoutParams);
//        //设置动画
//        beginDelayedTransition(mSearchLayout);
//    }
//
//    private void reduce() {
//        //设置收缩状态时的布局
//        tvSearch.setText("搜索");
//        RelativeLayout.LayoutParams LayoutParams = (RelativeLayout.LayoutParams) mSearchLayout.getLayoutParams();
//        LayoutParams.width = dip2px(80);
//        LayoutParams.setMargins(dip2px(10), dip2px(10), dip2px(10), dip2px(10));
//        mSearchLayout.setLayoutParams(LayoutParams);
//        //设置动画
//        beginDelayedTransition(mSearchLayout);
//    }
//
//    void beginDelayedTransition(ViewGroup view) {
//        mSet = new AutoTransition();
//        //设置动画持续时间
//        mSet.setDuration(300);
//        // 开始表演
//        TransitionManager.beginDelayedTransition(view, mSet);
//    }
//    其中 mSearchLayout 就是搜索框的布局，只需要动态设置一下伸展和收缩的布局大小和其中显示的文字，剩下的就交给 Transition 吧~ 这样搜索框就可以来回摇摆了。。
//
//
//
//    搜索栏伸展和收缩的时机：
//
//    观察一下效果，伸展的时机是当顶部完全盖住 banner 的时候开始的，收缩的时机是滚动到顶部的时候触发。需要我们监听 scllerview 的滚动状态。这里的顶部我是用了自定义布局的 toolbar，然后用一个 imageview 代替了 banner。
//
//            //scrollview滚动状态监听
//            mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
//        @Override
//        public void onScrollChanged() {
//            //改变toolbar的透明度
//            changeToolbarAlpha();
//            //滚动距离>=大图高度-toolbar高度 即toolbar完全盖住大图的时候 且不是伸展状态 进行伸展操作
//            if (mScrollView.getScrollY() >=ivImg.getHeight() - toolbar.getHeight()  && !isExpand) {
//                expand();
//                isExpand = true;
//            }
//            //滚动距离<=0时 即滚动到顶部时  且当前伸展状态 进行收缩操作
//            else if (mScrollView.getScrollY()<=0&& isExpand) {
//                reduce();
//                isExpand = false;
//            }
//        }
//    });
//}
//    当然简书的整个布局是基于 recyclerview 的，这里我为了方便使用了 scrollerview。recyclerview 也只需监听相应的滚动状态即可。
//
//
//
//        顶部透明度的渐变
//
//        直接上代码
//
//private void changeToolbarAlpha() {
//        int scrollY = mScrollView.getScrollY();
//        //快速下拉会引起瞬间scrollY<0
//        if(scrollY<0){
//        toolbar.getBackground().mutate().setAlpha(0);
//        return;
//        }
//        //计算当前透明度比率
//        float radio= Math.min(1,scrollY/(ivImg.getHeight()-toolbar.getHeight()*1f));
//        //设置透明度
//        toolbar.getBackground().mutate().setAlpha( (int)(radio * 0xFF));
//        }
//        注意刚才监听滚动事件的时候调用 changeToolbarAlpha() 方法，并且需要初始设置为全透明
//        toolbar.getBackground().mutate().setAlpha(0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jianshu_search);
      //  ButterKnife.bind(this);
        toolbar = findViewById(R.id.toolbar);
        ivImg = findViewById(R.id.iv_img);
        mScrollView = findViewById(R.id.scrollView);
        mSearchLayout = findViewById(R.id.ll_search);
        tvSearch = findViewById(R.id.tv_search);
        //设置全屏透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup rootView = (ViewGroup) ((ViewGroup)findViewById(android.R.id.content)).getChildAt(0);
            ViewCompat.setFitsSystemWindows(rootView,false);
            rootView.setClipToPadding(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS|
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN| View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        //设置toolbar初始透明度为0
        toolbar.getBackground().mutate().setAlpha(0);
        //scrollview滚动状态监听
        mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                //改变toolbar的透明度
                changeToolbarAlpha();
                //滚动距离>=大图高度-toolbar高度 即toolbar完全盖住大图的时候 且不是伸展状态 进行伸展操作
                if (mScrollView.getScrollY() >=ivImg.getHeight() - toolbar.getHeight()  && !isExpand) {
                    expand();
                    isExpand = true;
                }
                //滚动距离<=0时 即滚动到顶部时  且当前伸展状态 进行收缩操作
                else if (mScrollView.getScrollY()<=0&& isExpand) {
                    reduce();
                    isExpand = false;
                }
            }
        });
    }

    private void changeToolbarAlpha() {
        int scrollY = mScrollView.getScrollY();
        //快速下拉会引起瞬间scrollY<0
        if(scrollY<0){
            toolbar.getBackground().mutate().setAlpha(0);
            return;
        }
        //计算当前透明度比率
        float radio= Math.min(1,scrollY/(ivImg.getHeight()-toolbar.getHeight()*1f));
        //设置透明度
        toolbar.getBackground().mutate().setAlpha( (int)(radio * 0xFF));
    }


    private void expand() {
        //设置伸展状态时的布局
        tvSearch.setText("搜索简书的内容和朋友");
        RelativeLayout.LayoutParams LayoutParams = (RelativeLayout.LayoutParams) mSearchLayout.getLayoutParams();
        LayoutParams.width = LayoutParams.MATCH_PARENT;
        LayoutParams.setMargins(dip2px(10), dip2px(10), dip2px(10), dip2px(10));
        mSearchLayout.setLayoutParams(LayoutParams);
        //开始动画
        beginDelayedTransition(mSearchLayout);
    }

    private void reduce() {
        //设置收缩状态时的布局
        tvSearch.setText("搜索");
        RelativeLayout.LayoutParams LayoutParams = (RelativeLayout.LayoutParams) mSearchLayout.getLayoutParams();
        LayoutParams.width = dip2px(80);
        LayoutParams.setMargins(dip2px(10), dip2px(10), dip2px(10), dip2px(10));
        mSearchLayout.setLayoutParams(LayoutParams);
        //开始动画
        beginDelayedTransition(mSearchLayout);
    }

    void beginDelayedTransition(ViewGroup view) {
        mSet = new AutoTransition();
        mSet.setDuration(300);
        TransitionManager.beginDelayedTransition(view, mSet);
    }

    private int dip2px(float dpVale) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpVale * scale + 0.5f);
    }
}
