package com.newsuper.code.wechat;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;

import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.newsuper.code.R;

public class WechatRecyclerView extends RecyclerView {
    private float lastY;
    private View headView, ball1, ball2, ball3, layout_behand, layout_ball, bglayout, layout_title;
    private boolean isDrag; //头部布局落下以后 是否又进行过滑动
    private int lastValue;
    private MoveCallback moveListener;

    private int HEIGHT_INITIAL; //标题栏高度，也是header的初始高度
    private int HEIGHT_SHOWBALL_END; //小球动画结束高度
    private int BALLMAXRADIUS; //小球最大半径
    private int BALLMIDRADIUS; //小球动画结束时半径
    private int MAXHEIGHT;  //header布局完全落下的高度
    private int TOTALDISTANCE; //标题栏距离顶部最大高度

    private final int HEIGHT_SHOWBALL_BEGIN = 40; //小球动画出现时距顶部高度
    private final int BALLMAXDISTANCE=60; //小球之间的最大间隔
    private final int MAXDISTANCE_ALPHACHANGE=2000; //头部view放缩过程中头部高度变化的最大值
    private final float DUMP=1.5f; //阻尼系数

    private  int STATUS_NORMARL = 1;//header非落下状态
    private  int STATUS_DOWN = 2; //header落下状态
    private  int status = STATUS_NORMARL; //header状态

    public WechatRecyclerView(@NonNull Context context) {
        super(context);
        init();
    }

    public WechatRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WechatRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void setMoveListener(MoveCallback listener) {
        moveListener = listener;
    }
//    <?xml version="1.0" encoding="utf-8"?>
//<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
//    android:orientation="vertical" android:layout_width="match_parent"
//    android:layout_height="match_parent">
//    <LinearLayout
//    android:id="@+id/layout2"
//    android:layout_width="match_parent"
//    android:layout_height="match_parent"
//    android:orientation="horizontal"></LinearLayout>
//    <LinearLayout
//    android:id="@+id/layout1"
//    android:layout_width="match_parent"
//    android:layout_height="match_parent"
//    android:orientation="horizontal"></LinearLayout>
//</RelativeLayout>


    private void init() {
        HEIGHT_INITIAL = Util.dip2px(getContext(), 60);
        BALLMAXRADIUS = Util.dip2px(getContext(), 10);
        BALLMIDRADIUS = Util.dip2px(getContext(), 6);
        HEIGHT_SHOWBALL_END = (int) getResources().getDimension(R.dimen.maxheight_balllayout);
        TOTALDISTANCE = Util.getScreenHeight(getContext()) - HEIGHT_INITIAL;
        MAXHEIGHT = Util.getScreenHeight(getContext());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        if (status == STATUS_DOWN) {
            return false;
        }
        return super.onInterceptTouchEvent(e);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float dy = e.getRawY();
        switch (e.getActionMasked()) {
            case MotionEvent.ACTION_MOVE:
                if (status == STATUS_DOWN) { //header落下状态
                    if (getChildAt(0).getTop() < 0) {  //判断在布局落下状态后是否又进行了滑动
                        isDrag = true;
                    }
                    setBalllayoutHeight(TOTALDISTANCE + (float) (getChildAt(0).getTop()));
                    return super.onTouchEvent(e);
                }
                //header非落下状态
                if (!canScroll()) {
                    if (lastY == 0) {
                        lastY = dy;
                        return true;
                    } else {
                        if (dy - lastY > 0) { //下拉
                            if (headView.getHeight() < Util.getScreenHeight(getContext())) {
                                setHeaderHeightBy((int) ((dy - lastY) / DUMP)); //下拉过程添加一个阻尼效果
                                lastY = dy;
                            }
                            return true;
                        } else {   //上拉
                            if (headView.getHeight() >= HEIGHT_INITIAL) {
                                setHeaderHeightBy((int) (dy - lastY));
                                lastY = dy;
                                return true;
                            }
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                lastY = 0;
                if (status == STATUS_NORMARL) { //header非落下状态
                    if (headView.getHeight() < HEIGHT_SHOWBALL_END + HEIGHT_INITIAL) {
                        excuteAnnimation(headView.getHeight(), HEIGHT_INITIAL, false);
                    } else {
                        excuteAnnimation(headView.getHeight(), MAXHEIGHT, true);
                    }
                } else if (status == STATUS_DOWN) {  //header落下状态
                    if (findViewHolderForAdapterPosition(1) != null) { //position为1的位置可见时，即title不在最下部，弹回顶部
                        //  findViewHolderForAdapterPosition(1).itemView.getLocationOnScreen(location);
                        int distance = TOTALDISTANCE + getChildAt(0).getTop();
                        excuteBackAnimation(distance);
                    } else {  //如果title在最下部，并且之前没有进行滑动动作，回弹顶部，否则认为是人为又滑动到底部，此时不回弹
                        if (!isDrag) {
                            excuteAnnimation(headView.getHeight(), HEIGHT_INITIAL, true);
                        }
                    }
                    isDrag = false;
                    return true;
                }
                break;
        }
        return super.onTouchEvent(e);

    }

    /**
     * 头部运动动画
     * @param height
     * @param desheight
     * @param changeStatus
     */
    private void excuteAnnimation(int height, int desheight, final boolean changeStatus) {
        ValueAnimator animator;
        animator = ValueAnimator.ofInt(height, desheight);
        animator.setDuration(300);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setHeaderHeight((Integer) animation.getAnimatedValue());
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (changeStatus) {
                    if (status == STATUS_DOWN) {
                        status = STATUS_NORMARL;
                    } else {
                        status = STATUS_DOWN;
                    }
                }
            }
            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    /**
     * 头部落下 手动滑动返回时回弹动画
     * @param distance
     */
    private void excuteBackAnimation(final int distance) {
        ValueAnimator animator = ValueAnimator.ofInt(distance, 0);
        animator.setDuration(300);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                if (lastValue == 0) {
                    lastValue = distance;
                }
                scrollBy(0, lastValue - value);
                bglayout.setAlpha(getValue(bglayout.getAlpha(), 0, 1 - (float) value / distance));
                if ((float) value / TOTALDISTANCE > 0.7) {
                    layout_title.setBackgroundColor((Integer) Util.evaluate(1 - (float) ((value - TOTALDISTANCE * 0.7) / (0.3 * TOTALDISTANCE)),
                            0xff5C5772, 0xff777582));
                } else {
                    layout_title.setBackgroundColor(Color.parseColor("#00000000"));
                }
                layout_title.setAlpha(getValue(0.5f, 1,
                        1 - (float) (value - HEIGHT_SHOWBALL_END) / (TOTALDISTANCE - HEIGHT_SHOWBALL_END)));
                lastValue = value;
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setHeaderHeight(HEIGHT_INITIAL);
                scrollToPosition(0);
                status = STATUS_NORMARL;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    private float getValue(float startValue, float endValue, float fraction) {
        return startValue + (endValue - startValue) * fraction;
    }

    /**
     * 设置小球布局高度
     * @param height
     */
    private void setBalllayoutHeight(float height) {
        if (height <= HEIGHT_SHOWBALL_END) {
            ViewGroup.LayoutParams layoutParams = layout_ball.getLayoutParams();
            layoutParams.height = (int) height;
            layout_ball.setLayoutParams(layoutParams);
        }
        doAllAnimation(height);
    }

    /**
     * 滑动过程中各种动画效果
     * @param distance
     */
    private void doAllAnimation(float distance) {
        //-----------------------------------小球动画开始---------------------------------------
        if (distance > HEIGHT_SHOWBALL_BEGIN) {
            ball1.setVisibility(VISIBLE);
        } else {
            ball1.setVisibility(GONE);
        }
        float ballradius = 0;
        float totaldistasnce = HEIGHT_SHOWBALL_END - HEIGHT_SHOWBALL_BEGIN; //小球开始动画到执行动画结束的距离
        float adydistance = distance - HEIGHT_SHOWBALL_BEGIN;  // 小球开始动画后的下拉距离
        if (adydistance < totaldistasnce) {
            if (adydistance / totaldistasnce < 0.4) {
                ballradius = getValue(0, BALLMAXRADIUS, (float) (adydistance / (totaldistasnce * 0.4)));
            } else if (adydistance / totaldistasnce < 0.5) {
                ballradius = BALLMAXRADIUS;
            } else {
                ballradius = getValue(BALLMAXRADIUS, BALLMIDRADIUS, ((adydistance - totaldistasnce / 2) / (totaldistasnce / 2)));
            }
            setBallHeight(ball1, (int) ballradius);
        }

        if ((adydistance / totaldistasnce) > 0.4 && (adydistance / totaldistasnce) < 1.0) {
            ball2.setVisibility(VISIBLE);
            ball3.setVisibility(VISIBLE);
            setBallHeight(ball2, BALLMIDRADIUS);
            setBallHeight(ball3, BALLMIDRADIUS);
            int transX = (int) (((adydistance - totaldistasnce * 0.4) / (totaldistasnce * 0.6)) * 60);
            ball2.setTranslationX(transX);
            ball3.setTranslationX(-transX);
        } else {
            if ((adydistance / totaldistasnce) >= 1.0) {
                ball2.setTranslationX(BALLMAXDISTANCE);
                ball3.setTranslationX(-BALLMAXDISTANCE);
            } else {
                ball2.setVisibility(GONE);
                ball3.setVisibility(GONE);
            }
        }
        layout_ball.setAlpha(1 - (distance - HEIGHT_SHOWBALL_END) / 200);

        //-----------------------------------小球动画结束---------------------------------------



        bglayout.setAlpha((distance - HEIGHT_SHOWBALL_END) / (TOTALDISTANCE - HEIGHT_SHOWBALL_END)); //透明度变化
        if (status == STATUS_NORMARL) {     //大小变化
            layout_behand.setPivotY(0);
            layout_behand.setScaleX((float) (0.7 + (distance < MAXDISTANCE_ALPHACHANGE ? distance / MAXDISTANCE_ALPHACHANGE * 0.3 : 0.3)));
            layout_behand.setScaleY((float) (0.7 + (distance < MAXDISTANCE_ALPHACHANGE ? distance / MAXDISTANCE_ALPHACHANGE * 0.3 : 0.3)));
        }
        //标题栏背景透明度变化
        layout_title.setAlpha(getValue(1, 0.5f,
                (distance - HEIGHT_SHOWBALL_END) / (TOTALDISTANCE - HEIGHT_SHOWBALL_END)));
        //标题栏背景颜色变化
        if (distance / TOTALDISTANCE > 0.7) {
            layout_title.setBackgroundColor((Integer) Util.evaluate((float) ((distance - TOTALDISTANCE * 0.7) / (0.3 * TOTALDISTANCE)),
                    0xff777582, 0xff5C5772));
        } else {
            layout_title.setBackgroundColor(Color.parseColor("#00000000"));
        }


    }


    public void setBallHeight(View view, int height) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = height;
        layoutParams.width = height;
        view.setLayoutParams(layoutParams);
    }

    /**
     * 设置头部高度
     * @param height
     */
    public void setHeaderHeight(int height) {
        ViewGroup.LayoutParams layoutParams = headView.getLayoutParams();
        layoutParams.height = height;
        headView.setLayoutParams(layoutParams);
        headView.post(new Runnable() {
            @Override
            public void run() {
                if (headView.getHeight() > HEIGHT_INITIAL) {
                    moveListener.onMove(true);
                    doAllAnimation(headView.getHeight() - HEIGHT_INITIAL);
                } else {
                    moveListener.onMove(false);
                }
            }
        });
    }

    /**
     * 滑动过程设置头部高度
     * @param dy
     */
    public void setHeaderHeightBy(int dy) {
        ViewGroup.LayoutParams layoutParams = headView.getLayoutParams();
        layoutParams.height = layoutParams.height + dy > Util.getScreenHeight(getContext()) ?
                Util.getScreenHeight(getContext()) : layoutParams.height + dy;
        headView.setLayoutParams(layoutParams);
        post(new Runnable() {
            @Override
            public void run() {
                if (headView.getHeight() > HEIGHT_INITIAL) {
                    ViewGroup.LayoutParams layoutParams = layout_ball.getLayoutParams();
                    layoutParams.height = headView.getHeight() > HEIGHT_SHOWBALL_END ? HEIGHT_SHOWBALL_END : headView.getHeight();
                    layout_ball.setLayoutParams(layoutParams);
                    doAllAnimation(headView.getHeight() - HEIGHT_INITIAL);
                    moveListener.onMove(true);
                } else {
                    moveListener.onMove(false);
                }
            }
        });
    }

    /**
     * 判断是否滑动到顶部
     * @return
     */
    private boolean canScroll() {
        return canScrollVertically(-1);
    }


    public void setHeadView(View view) {
        this.headView = view;
        ball1 = headView.findViewById(R.id.ball1);
        ball2 = headView.findViewById(R.id.ball2);
        ball3 = headView.findViewById(R.id.ball3);
        layout_ball = headView.findViewById(R.id.layout_ball);
        layout_behand = headView.findViewById(R.id.layout_behand);
        bglayout = headView.findViewById(R.id.bglayout);
        layout_title = headView.findViewById(R.id.layout_title);
        ViewGroup.LayoutParams layoutParams = layout_behand.getLayoutParams();
        layoutParams.height = Util.getScreenHeight(getContext()) - HEIGHT_INITIAL - Util.dip2px(getContext(), 20);
        layout_behand.setLayoutParams(layoutParams);
        setHeaderHeight(HEIGHT_INITIAL);
    }

    public interface MoveCallback {
        void onMove(boolean isMove);
    }

}