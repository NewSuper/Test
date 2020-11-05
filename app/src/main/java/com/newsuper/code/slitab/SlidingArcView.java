package com.newsuper.code.slitab;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.newsuper.code.R;
import com.newsuper.code.whiteboard.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 滑动view
 */
public class SlidingArcView extends ViewGroup {
    public static String TAG = "QTView";
    private String titles[] = {"商铺", "项目", "名片", "导航", "个人网页"};
    private int src[] = {R.mipmap.shangpu, R.mipmap.xiangmu, R.mipmap.mingpian, R.mipmap.daohang, R.mipmap.wangye};
    private List<SignView> views = new ArrayList<>();
    private Bitmap chooseBit;
    // private Bitmap centerbit;
    // private Bitmap leftM;
    //  private Bitmap rightM;

    public SlidingArcView(Context context) {
        this(context, null);
    }

    public SlidingArcView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    int mSize;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mSize = Math.min(getMeasuredWidth(), getMeasuredHeight());
        setMeasuredDimension(mSize, mSize);
    }


    public int blength(String value) {
        float valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        String big = "[A-Z]";
        String small = "[a-z]";
        String number = "[0-9]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else if (temp.matches(big)) {
                valueLength += 1.58f;
            } else if (temp.matches(small)) {
                valueLength += 1.25f;
            } else if (temp.matches(number)) {
                valueLength += 1.335f;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return (int) (valueLength);
    }

    //    public  int blength(String value) {
//        int valueLength = 0;
//        String chinese = "[\u0391-\uFFE5]";
//        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
//        for (int i = 0; i < value.length(); i++) {
//            /* 获取一个字符 */
//            String temp = value.substring(i, i + 1);
//            /* 判断是否为中文字符 */
//            if (temp.matches(chinese)) {
//                /* 中文字符长度为2 */
//                valueLength += 2;
//            } else {
//                /* 其他字符长度为1 */
//                valueLength += 1;
//            }
//        }
//        return valueLength;
//    }
    private void init() {
        chooseBit = ((BitmapDrawable) getResources().getDrawable(R.mipmap.choose)).getBitmap();
        //   centerbit = ((BitmapDrawable) getResources().getDrawable(R.mipmap.yuyin)).getBitmap();
        //   leftM = ((BitmapDrawable) getResources().getDrawable(R.mipmap.chart)).getBitmap();
        //   rightM = ((BitmapDrawable) getResources().getDrawable(R.mipmap.voice)).getBitmap();
        CentX = ScreenUtils.getScreenW() / 2;
        CentY = ScreenUtils.getScreenW() / 2 + viewTopChange + ScreenUtils.dp2px(150);
        RADIUS = ScreenUtils.getScreenW() / 2 + ScreenUtils.dp2px(115);
        this.removeAllViews();
        views.clear();
        for (int i = 0, len = src.length; i < len; i++) {
            View v = new View(getContext());
            v.setBackgroundResource(src[i]);

            TextView textView = new TextView(getContext());
            textView.setText(titles[i]);
            textView.setTextSize(12);
            textView.setTextColor(0xffffffff);

            SignView signView = new SignView(v, textView, i);
            views.add(signView);
            this.addView(v);
            this.addView(textView);
        }
        setBackgroundColor(getResources().getColor(android.R.color.transparent));
        this.setClickable(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制背景
        DrawCircleColorBg(canvas);
        //绘制选中
        DrawChooseBt(canvas);
        //绘制菜单
        //  DrawCenterM(canvas);
        //  DrawLeftM(canvas);
        //  DrawRightM(canvas);
        //绘制图标
        for (SignView view : views) {
            view.flush();
        }
    }

    /**
     * 绘制圆形背景
     *
     * @param canvas
     */
    private void DrawCircleColorBg(Canvas canvas) {
        Paint paint = new Paint();
        //抗锯齿
        paint.setAntiAlias(true);
        //背景颜色
        paint.setColor(0xff1a1a1a);
        //绘制实心圆
        canvas.drawCircle(CentX, CentY - viewTopChange, RADIUS + ScreenUtils.dp2px(34f), paint);

    }

    /**
     * 绘制选中图标
     *
     * @param canvas
     */
    private void DrawChooseBt(Canvas canvas) {
        float wScale = (float) ScreenUtils.dp2px(40) / chooseBit.getWidth();
        float hScale = (float) ScreenUtils.dp2px(40) / chooseBit.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(wScale, hScale);
        Bitmap result = Bitmap.createBitmap(chooseBit, 0, 0, chooseBit.getWidth(), chooseBit.getHeight(), matrix, true);
        canvas.drawBitmap(result, CentX - ScreenUtils.dp2px(40) / 2, CentY - RADIUS - viewTopChange - ScreenUtils.dp2px(40) / 2, new Paint());
    }

//    private int CenterMX;
//    private int CenterMY;
//    private int LeftMX;
//    private int LeftMY;
//    private int RightMX;
//    private int RightMY;

    /**
     * 绘制中间菜单键
     *
     * @param canvas
     */
//    private void DrawCenterM(Canvas canvas) {
//        float wScale = (float) ScreenUtils.dp2px(74) / centerbit.getWidth();
//        float hScale = (float) ScreenUtils.dp2px(70) / centerbit.getHeight();
//        Matrix matrix = new Matrix();
//        matrix.postScale(wScale, hScale);
//        Bitmap result = Bitmap.createBitmap(centerbit, 0, 0, centerbit.getWidth(), centerbit.getHeight(), matrix, true);
//        canvas.drawBitmap(result, CentX - ScreenUtils.dp2px(74) / 2, getMeasuredHeight() - ScreenUtils.dp2px(82), new Paint());
//        CenterMX = CentX;
//        CenterMY = getMeasuredHeight() - ScreenUtils.dp2px(82) + ScreenUtils.dp2px(35);
//    }

    /**
     * 绘制左边菜单
     *
     * @param canvas
     */
//    private void DrawLeftM(Canvas canvas) {
//        float wScale = (float) ScreenUtils.dp2px(24) / leftM.getWidth();
//        float hScale = (float) ScreenUtils.dp2px(24) / leftM.getHeight();
//        Matrix matrix = new Matrix();
//        matrix.postScale(wScale, hScale);
//        Bitmap result = Bitmap.createBitmap(leftM, 0, 0, leftM.getWidth(), leftM.getHeight(), matrix, true);
//        canvas.drawBitmap(result, CentX - ScreenUtils.dp2px(74) / 2 - ScreenUtils.dp2px(71), getMeasuredHeight() - ScreenUtils.dp2px(49), new Paint());
//        LeftMX = CentX - ScreenUtils.dp2px(74) / 2 - ScreenUtils.dp2px(71) + ScreenUtils.dp2px(12);
//        LeftMY = getMeasuredHeight() - ScreenUtils.dp2px(49) + ScreenUtils.dp2px(12);
//    }

    /**
     * 绘制右边菜单
     *
     * @param canvas
     */
//    private void DrawRightM(Canvas canvas) {
//        float wScale = (float) ScreenUtils.dp2px(24) / rightM.getWidth();
//        float hScale = (float) ScreenUtils.dp2px(24) / rightM.getHeight();
//        Matrix matrix = new Matrix();
//        matrix.postScale(wScale, hScale);
//        Bitmap result = Bitmap.createBitmap(rightM, 0, 0, rightM.getWidth(), rightM.getHeight(), matrix, true);
//        canvas.drawBitmap(result, CentX - ScreenUtils.dp2px(74) / 2 + ScreenUtils.dp2px(120), getMeasuredHeight() - ScreenUtils.dp2px(49), new Paint());
//        RightMX = CentX - ScreenUtils.dp2px(74) / 2 + ScreenUtils.dp2px(120) + ScreenUtils.dp2px(12);
//        RightMY = getMeasuredHeight() - ScreenUtils.dp2px(49) + ScreenUtils.dp2px(12);
//    }

    boolean canScroll = true;
    int lastX;
    int downPointId;
    int downX;
    int downY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isAnimated) {//正在运行动画中
            return super.onTouchEvent(event);
        }
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();//获得VelocityTracker类实例
        }
        mVelocityTracker.addMovement(event);//将事件加入到VelocityTracker类实例中
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (canScroll) {
                    flushViews((int) (event.getX() - lastX) / 2);
                    lastX = (int) event.getX();
                    invalidate();
                }
                return true;
            case MotionEvent.ACTION_UP:
                //先判断是否是点击事件
                final int pi = event.findPointerIndex(downPointId);
                if (isClickable() && ((Math.abs(event.getX(pi) - downX) <= 3) && Math.abs(event.getY(pi) - downY) <= 3)) {
                    if (isFocusable() && isFocusableInTouchMode() && !isFocused())
                        requestFocus();
                    performViewClick();
                    return true;
                }
                //判断当ev事件是MotionEvent.ACTION_UP时：计算速率
                final VelocityTracker velocityTracker = mVelocityTracker;
                // 1000 provides pixels per second
                velocityTracker.computeCurrentVelocity(1, (float) 0.01);
                velocityTracker.computeCurrentVelocity(1000);//设置units的值为1000，意思为一秒时间内运动了多少个像素
                if (velocityTracker.getXVelocity() > 2000 || velocityTracker.getXVelocity() < -2000) {//自动滚动最低要求
                    autoTime = (int) (velocityTracker.getXVelocity() / 1000 * 200);
                    autoTime = autoTime > 1500 ? 1500 : autoTime;
                    autoTime = autoTime < -1500 ? -1500 : autoTime;
                    isAnimated = true;
                    handler.sendEmptyMessageDelayed(1, 10);
                } else {
                    isAnimated = false;
                    resetView();
                }
                return true;
            case MotionEvent.ACTION_DOWN:
                downPointId = event.getPointerId(0);
                downX = lastX = (int) event.getX();
                downY = (int) event.getY();
                return true;
        }
        return super.onTouchEvent(event);
    }
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (isAnimated) {//正在运行动画中
//            return super.onTouchEvent(event);
//        }
//        if (mVelocityTracker == null) {
//            mVelocityTracker = VelocityTracker.obtain();//获得VelocityTracker类实例
//        }
//        mVelocityTracker.addMovement(event);//将事件加入到VelocityTracker类实例中
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_MOVE:
//                if (canScroll) {
//                    flushViews((int) (event.getX() - lastX));
//                    lastX = (int) event.getX();
//                    invalidate();
//                }
//                return true;
//            case MotionEvent.ACTION_UP:
//                //先判断是否是点击事件
//                final int pi = event.findPointerIndex(downPointId);
//                if (isClickable() && ((Math.abs(event.getX(pi) - downX) <= 3) && Math.abs(event.getY(pi) - downY) <= 3)) {
//                    if (isFocusable() && isFocusableInTouchMode() && !isFocused())
//                        requestFocus();
//                    performViewClick();
//                    return true;
//                }
//                //判断当ev事件是MotionEvent.ACTION_UP时：计算速率
//                final VelocityTracker velocityTracker = mVelocityTracker;
//                // 1000 provides pixels per second
//                velocityTracker.computeCurrentVelocity(1, (float) 0.01);
//                velocityTracker.computeCurrentVelocity(1000);//设置units的值为1000，意思为一秒时间内运动了多少个像素  
//                if (velocityTracker.getXVelocity() > 2000 || velocityTracker.getXVelocity() < -2000) {//自动滚动最低要求
//                    autoTime = (int) (velocityTracker.getXVelocity() / 1000 * 200);
//                    autoTime = autoTime > 1500 ? 1500 : autoTime;
//                    autoTime = autoTime < -1500 ? -1500 : autoTime;
//                    isAnimated = true;
//                    handler.sendEmptyMessageDelayed(1, 10);
//                } else {
//                    isAnimated = false;
//                    resetView();
//                }
//                return true;
//            case MotionEvent.ACTION_DOWN:
//                downPointId = event.getPointerId(0);
//                downX = lastX = (int) event.getX();
//                downY = (int) event.getY();
//                return true;
//        }
//        return super.onTouchEvent(event);
//    }

    /**
     * 这个方法只能点击图标响应
     */
//    private void performViewClick() {
//        for (SignView signView : views) {
//            Rect r = new Rect(signView.centX - signView.size / 2, signView.centY - signView.size / 2 - viewTopChange, signView.centX + signView.size / 2, signView.centY + signView.size / 2 - viewTopChange);
//            if (r.contains(downX, downY)) {
//                if (qtItemClickListener != null && !isAnimated) {
//                    isClick = true;
//                    chooseView = signView;
//                    autoScrollX = ScreenUtils.getScreenW() / 2 - signView.centX;
//                    handler.sendEmptyMessageDelayed(0, 10);
//                }
//            }
//        }
//        Rect center = new Rect(CenterMX - ScreenUtils.dp2px(73) / 2, CenterMY - ScreenUtils.dp2px(70) / 2, CenterMX + ScreenUtils.dp2px(73) / 2, CenterMY + ScreenUtils.dp2px(70) / 2);
//        Rect left = new Rect(LeftMX - ScreenUtils.dp2px(24) / 2, LeftMY - ScreenUtils.dp2px(24) / 2, LeftMX + ScreenUtils.dp2px(24) / 2, LeftMY + ScreenUtils.dp2px(24) / 2);
//        Rect right = new Rect(RightMX - ScreenUtils.dp2px(24) / 2, RightMY - ScreenUtils.dp2px(24) / 2, RightMX + ScreenUtils.dp2px(24) / 2, RightMY + ScreenUtils.dp2px(24) / 2);
//        if (center.contains(downX, downY)) {
//            handler.sendEmptyMessageDelayed(3, 10);
//        }
//        if (left.contains(downX, downY)) {
//            handler.sendEmptyMessageDelayed(4, 10);
//        }
//        if (right.contains(downX, downY)) {
//            handler.sendEmptyMessageDelayed(5, 10);
//        }
//    }

    /**
     * 图标和文字区域都能点击的方法
     */
//    private void performViewClick() {
//        for (SignView signView : views) {
//            int left = (signView.centX - signView.size / 2)<=(signView.centX-ScreenUtils.dp2px(12)*titles[signView.index].length()/2)?(signView.centX - signView.size / 2):(signView.centX-ScreenUtils.dp2px(12)*titles[signView.index].length()/2);
//            int right = (signView.centX + signView.size / 2)>=(signView.centX+ScreenUtils.dp2px(12)*titles[signView.index].length()/2)?(signView.centX + signView.size / 2):(signView.centX+ScreenUtils.dp2px(12)*titles[signView.index].length()/2);
//            int top = signView.centY - signView.size / 2 - viewTopChange;
//            int bottom = signView.centY + signView.size/2+ScreenUtils.dp2px(32f)  - viewTopChange;
//            Rect r = new Rect(left, top, right, bottom);
//            if (r.contains( downX, downY)) {
//                if (qtItemClickListener != null && !isAnimated) {
//                    isClick=true;
//                    chooseView =signView;
//                    autoScrollX = ScreenUtils.getScreenW() / 2 - signView.centX;
//                    handler.sendEmptyMessageDelayed(0, 10);
//                }
//            }
//        }
//        Rect center = new Rect(CenterMX-ScreenUtils.dp2px(73)/2, CenterMY-ScreenUtils.dp2px(70)/2, CenterMX+ScreenUtils.dp2px(73)/2, CenterMY+ScreenUtils.dp2px(70)/2);
//        Rect left = new Rect(LeftMX-ScreenUtils.dp2px(24)/2, LeftMY-ScreenUtils.dp2px(24)/2, LeftMX+ScreenUtils.dp2px(24)/2, LeftMY+ScreenUtils.dp2px(24)/2);
//        Rect right = new Rect(RightMX-ScreenUtils.dp2px(24)/2, RightMY-ScreenUtils.dp2px(24)/2, RightMX+ScreenUtils.dp2px(24)/2, RightMY+ScreenUtils.dp2px(24)/2);
//        if(center.contains(downX, downY)){
//            handler.sendEmptyMessageDelayed(3,10);
//        }
//        if(left.contains(downX, downY)){
//            handler.sendEmptyMessageDelayed(4, 10);
//        }
//        if(right.contains(downX, downY)){
//            handler.sendEmptyMessageDelayed(5, 10);
//        }
//
//    }
    private void performViewClick() {
        for (SignView signView : views) {
            int left = (signView.centX - signView.size / 2) <= (signView.centX - ScreenUtils.dp2px(12) * (blength(titles[signView.index]) / 2) / 2) ? (signView.centX - signView.size / 2) : (signView.centX - ScreenUtils.dp2px(12) * (blength(titles[signView.index]) / 2) / 2);
            int right = (signView.centX + signView.size / 2) >= (signView.centX + ScreenUtils.dp2px(12) * (blength(titles[signView.index]) / 2) / 2) ? (signView.centX + signView.size / 2) : (signView.centX + ScreenUtils.dp2px(12) * (blength(titles[signView.index]) / 2) / 2);
            int top = signView.centY - signView.size / 2 - viewTopChange;
            int bottom = signView.centY + signView.size / 2 + ScreenUtils.dp2px(32f) - viewTopChange;
            Rect r = new Rect(left, top, right, bottom);
            if (r.contains(downX, downY)) {
                if (qtItemClickListener != null && !isAnimated) {
                    isClick = true;
                    chooseView = signView;
                    autoScrollX = ScreenUtils.getScreenW() / 2 - signView.centX;
                    handler.sendEmptyMessageDelayed(0, 10);
                }
            }
        }
        //  Rect center = new Rect(CenterMX - ScreenUtils.dp2px(73) / 2, CenterMY - ScreenUtils.dp2px(70) / 2, CenterMX + ScreenUtils.dp2px(73) / 2, CenterMY + ScreenUtils.dp2px(70) / 2);
        //  Rect left = new Rect(LeftMX - ScreenUtils.dp2px(24) / 2, LeftMY - ScreenUtils.dp2px(24) / 2, LeftMX + ScreenUtils.dp2px(24) / 2, LeftMY + ScreenUtils.dp2px(24) / 2);
        //  Rect right = new Rect(RightMX - ScreenUtils.dp2px(24) / 2, RightMY - ScreenUtils.dp2px(24) / 2, RightMX + ScreenUtils.dp2px(24) / 2, RightMY + ScreenUtils.dp2px(24) / 2);
//        if (center.contains(downX, downY)) {
//            handler.sendEmptyMessageDelayed(3, 10);
//        }
//        if (left.contains(downX, downY)) {
//            handler.sendEmptyMessageDelayed(4, 10);
//        }
//        if (right.contains(downX, downY)) {
//            handler.sendEmptyMessageDelayed(5, 10);
//        }

    }

    private void flushViews(int scrollX) {
        for (SignView view : views) {
            view.scroll(scrollX);
        }
    }

    //停止滚动，归位
    public void resetView() {
        for (SignView view : views) {
            if (view.centX > CentX && (view.centX - CentX < view.width)) {//屏幕右半部分移动运动，变小
                int dis = view.centX - CentX;
                if (dis > view.width / 2) {
                    autoScrollX = view.width - dis;
                } else {
                    autoScrollX = dis * -1;
                }
                break;
            }

        }
        handler.sendEmptyMessageDelayed(0, 10);
    }

    int veSpeed = 0;//松开自动滚动速度
    int autoTime = 0;//送开自动滚动
    int autoScrollX = 0;//归位滚动

    /**
     * 设置选中tab位置
     *
     * @param x src中位置
     */
    public void ChooseIndex(int x) {
        for (SignView signView : views) {
            Rect r = new Rect(signView.centX - signView.size / 2, signView.centY - signView.size / 2 - viewTopChange, signView.centX + signView.size / 2, signView.centY + signView.size / 2 - viewTopChange);
            if (signView.index == x) {
                if (qtItemClickListener != null && !isAnimated) {
                    isClick = true;
                    chooseView = signView;
                    autoScrollX = ScreenUtils.getScreenW() / 2 - signView.centX;
                    handler.sendEmptyMessageDelayed(0, 10);
                }
            }
        }
    }

    /**
     * 设置图标数组和下面显示文字数组，需要一一对应
     *
     * @param src    图片id数组
     * @param titles 对应文字数组
     */
    public void SetSrcAndTitles(int[] src, String[] titles) {
        this.src = src;
        this.titles = titles;
        init();
        handler.sendEmptyMessage(2);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    if (autoScrollX != 0) {
                        if (Math.abs(autoScrollX) > SPEED) {

                            SPEED = Math.abs(SPEED);
                            if (autoScrollX > 0) {
                                autoScrollX -= SPEED;
                            } else {
                                autoScrollX += SPEED;
                                SPEED = SPEED * -1;
                            }
                            for (SignView view : views) {
                                view.scroll(SPEED);
                            }
                        } else {

                            for (SignView view : views) {
                                view.scroll(autoScrollX);
                            }
                            autoScrollX = 0;
                            isAnimated = false;

                            if (chooseView != null && qtScrollListener != null && lastChooseView != chooseView) {
                                if (!isClick) {
                                    qtScrollListener.onSelect(chooseView.view, chooseView.index);
                                    lastChooseView = chooseView;
                                } else {
                                    qtItemClickListener.onClick(chooseView.view, chooseView.index);
                                    lastChooseView = chooseView;
                                    isClick = false;
                                }
                            }
                        }
                        invalidate();
                        handler.sendEmptyMessageDelayed(0, 10);
                    }
                    break;
                case 1:
//                    if (autoTime > 0) {
//                        if (autoTime > 1500) {
//                            veSpeed = 1;
//                        } else if (autoTime > 1000) {
//                            veSpeed = 1;
//                        } else if (autoTime > 500) {
//                            veSpeed = 1;
//                        } else if (autoTime > 200) {
//                            veSpeed = 1;
//                        } else {
//                            veSpeed = 1;
//                        }
//                        for (SignView view : views) {
//                            view.scroll(veSpeed);
//                        }
//                        autoTime -= 1500;
//                        if (autoTime < 0) {
//                            isAnimated = false;
//                            autoTime = 0;
//                        }
//                        invalidate();
//                        handler.sendEmptyMessageDelayed(1, 60);
//                    } else if (autoTime < 0) {
//                        if (autoTime < -1500) {
//                            veSpeed = -1;
//                        } else if (autoTime < -1000) {
//                            veSpeed = -1;
//                        } else if (autoTime < -500) {
//                            veSpeed = -1;
//                        } else if (autoTime < -200) {
//                            veSpeed = -1;
//                        } else {
//                            veSpeed = -1;
//                        }
//                        for (SignView view : views) {
//                            view.scroll(veSpeed);
//                        }
//                        autoTime += 1500;
//                        if (autoTime > 0) {
//                            isAnimated = false;
//                            autoTime = 0;
//                        }
//                        invalidate();
//                        handler.sendEmptyMessageDelayed(1, 60);
//                    } else {
//                        resetView();
//                        invalidate();
//                    }


                    if (autoTime > 0) {
                        if (autoTime > 1500) {
                            veSpeed = 80;
                        } else if (autoTime > 1000) {
                            veSpeed = 80;
                        } else if (autoTime > 500) {
                            veSpeed = 40;
                        } else if (autoTime > 200) {
                            veSpeed = 20;
                        } else {
                            veSpeed = 10;
                        }
                        for (SignView view : views) {
                            view.scroll(veSpeed);
                        }
                        autoTime -= 20;
                        if (autoTime < 0) {
                            isAnimated = false;
                            autoTime = 0;
                        }
                        invalidate();
                        handler.sendEmptyMessageDelayed(1, 20);
                    } else if (autoTime < 0) {
                        if (autoTime < -1500) {
                            veSpeed = -80;
                        } else if (autoTime < -1000) {
                            veSpeed = -60;
                        } else if (autoTime < -500) {
                            veSpeed = -40;
                        } else if (autoTime < -200) {
                            veSpeed = -20;
                        } else {
                            veSpeed = -10;
                        }
                        for (SignView view : views) {
                            view.scroll(veSpeed);
                        }
                        autoTime += 20;
                        if (autoTime > 0) {
                            isAnimated = false;
                            autoTime = 0;
                        }
                        invalidate();
                        handler.sendEmptyMessageDelayed(1, 20);
                    } else {
                        resetView();
                        invalidate();
                    }
                    break;
                case 2:
                    invalidate();
                    break;
//                case 3:
//                    if (qtMenuClickListner != null) {
//                        qtMenuClickListner.OnCenterMClick();
//                    }
//                    break;
//                case 4:
//                    if (qtMenuClickListner != null) {
//                        qtMenuClickListner.OnLeftMClick();
//                    }
//                    break;
//                case 5:
//                    if (qtMenuClickListner != null) {
//                        qtMenuClickListner.OnRightMClick();
//                    }
//                    break;
                default:
                    break;
            }
        }
    };

    /**
     * listener
     */
    QTScrollListener qtScrollListener;
    QTItemClickListener qtItemClickListener;
    // QtMenuClickListner qtMenuClickListner;

    public interface QTScrollListener {
        void onSelect(View v, int index);
    }

    public interface QTItemClickListener {
        void onClick(View v, int index);
    }

    public void setQtScrollListener(QTScrollListener qtScrollListener) {
        this.qtScrollListener = qtScrollListener;
    }

    public void setQtItemClickListener(QTItemClickListener qtItemClickListener) {
        this.qtItemClickListener = qtItemClickListener;
    }

//    public interface QtMenuClickListner {
//        void OnCenterMClick();
//
//        void OnLeftMClick();
//
//        void OnRightMClick();
//    }

//    public void setOnMenuClickListener(QtMenuClickListner qtMenuClickListner) {
//        this.qtMenuClickListner = qtMenuClickListner;
//    }

    private boolean isAnimated = false;//是否正在动画中
    private int viewTopChange = 0;//view往上偏移的位置
    private VelocityTracker mVelocityTracker;//速度跟踪
    private int SPEED = 30;//归位自动滚动速度
    private SignView leftView;//屏幕最左边的view
    private SignView rightView;//屏幕最右边的view
    private int CentX;//外层圆中心x
    private int CentY;//外层圆中心Y
    private int RADIUS;//外层圆半径
    private SignView chooseView;
    private SignView lastChooseView;
    private boolean isClick = false;

    private class SignView {
        private int indexInScreen;//在屏幕中的位置
        private View view;
        private View text;
        private String title;
        private int centX;//view的中心点坐标
        private int centY;
        private int index;
        private int size = ScreenUtils.dp2px(29);//view大小
        private int width = src.length >= 5 ? (ScreenUtils.getScreenW()) / 5 : (ScreenUtils.getScreenW()) / src.length;
        private boolean stop;//停止滚动，用来判断是否自动进行归位
        private boolean isChoose = false;

        public SignView(View v, View t, final int index) {
            this.index = index;
            this.view = v;
            this.text = t;
            this.title = titles[index];
            if (index == 0) {
                leftView = this;
            }
            if (index == src.length - 1) {
                rightView = this;
            }
            if (index == src.length / 2) {
                isChoose = true;
                chooseView = this;
            }
            initView();
        }

        //计算view的坐标
        private void initView() {
            centX = (width) / 2 + width * index;
            centY = CentY - (int) Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((centX - CentX), 2));
        }

        public void scroll(int scrollX) {
            this.centX += scrollX;
            centY = CentY - (int) Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((centX - CentX), 2));
        }

//        public int getCentX() {
//            return centX;
//        }
//
//        public int getCentY() {
//            return centY;
//        }

//        public View getView() {
//            return view;
//        }

        //        public void flush() {
//            clean();
//            //每次计算view的位置
//            //绘制图表
//            view.layout(centX - size / 2, centY - size / 2 - viewTopChange, centX + size / 2, centY + size / 2 - viewTopChange);
//            //绘制图标下方文字
//            text.layout(centX - ScreenUtils.dp2px(12) * titles[index].length() / 2, centY + size / 2 + ScreenUtils.dp2px(6) - viewTopChange, centX + ScreenUtils.dp2px(12) * titles[index].length() / 2, centY + size / 2 + ScreenUtils.dp2px(22.5f) - viewTopChange);
//            //以是否靠近中心点 来判断是否变大变小
//            if (centX >= CentX && centX - CentX <= size / 2) {//view的x偏离小于中心选中
//                isChoose = true;
//            } else if (centX <= CentX && CentX - centX <= size / 2) {//view的x偏离小于中心选中
//                isChoose = true;
//            } else {
//                isChoose = false;
//            }
//            if (isChoose) {
//                chooseView = this;
//            }
//        }
        public void flush() {
            clean();
            //每次计算view的位置
//绘制图表
            view.layout(centX - size / 2, centY - size / 2 - viewTopChange, centX + size / 2, centY + size / 2 - viewTopChange);
//绘制图标下方文字
            text.layout(centX - ScreenUtils.dp2px(12) * (blength(titles[index]) / 2) / 2, centY + size / 2 + ScreenUtils.dp2px(6) - viewTopChange, centX + ScreenUtils.dp2px(12) * (blength(titles[index]) / 2) / 2, centY + size / 2 + ScreenUtils.dp2px(22.5f) - viewTopChange);
            //以是否靠近中心点 来判断是否变大变小
            if (centX >= CentX && centX - CentX <= size / 2) {//view的x偏离小于中心选中
                isChoose = true;
            } else if (centX <= CentX && CentX - centX <= size / 2) {//view的x偏离小于中心选中
                isChoose = true;
            } else {
                isChoose = false;
            }
            if (isChoose) {
                chooseView = this;
            }
        }

        //无限循环的判断
        private void clean() {
            if (leftView.notLeftView()) {//最左边没有view了，把最右边的移到最左边
                rightView.centX = leftView.centX - width;
                rightView.changeY();
                leftView = rightView;
                rightView = views.get(rightView.index == 0 ? views.size() - 1 : rightView.index - 1);
            }
            if (rightView.notRightView()) {//最右边没有view了，把最左边的移到最右边
                leftView.centX = rightView.centX + width;
                leftView.changeY();
                rightView = leftView;
                leftView = views.get(leftView.index == views.size() - 1 ? 0 : leftView.index + 1);
            }
        }

        //重新计算Y点坐标
        public void changeY() {
            centY = CentY - (int) Math.sqrt(Math.pow(RADIUS, 2) - Math.pow((centX - CentX), 2));
        }

        public boolean notLeftView() {
            return centX - width / 2 > width / 2;
        }

        public boolean notRightView() {
            return centX + width / 2 + width / 2 < ScreenUtils.getScreenW();
        }
    }
}