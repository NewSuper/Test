package com.newsuper.code.textpathview;


import android.animation.Animator;



public class PathAnimatorListener implements Animator.AnimatorListener {
    private PathView mPathView;
    protected boolean isCancel = false;

    protected void setTarget(PathView pathView) {
        this.mPathView = pathView;
    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    @Override
    public void onAnimationStart(Animator animation) {
        isCancel = false;
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        mPathView.setShowPainterActually(false);
    }

    @Override
    public void onAnimationCancel(Animator animation) {
        isCancel = true;
    }
}
