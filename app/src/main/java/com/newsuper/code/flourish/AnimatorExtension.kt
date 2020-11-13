package com.newsuper.code.flourish


import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.animation.AccelerateInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import android.view.animation.OvershootInterpolator

internal fun Animator.doAfterFinishAnimate(doAfterLift: () -> Unit) {
    this.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator?) {
            super.onAnimationEnd(animation)
            doAfterLift()
        }
    })
}

internal fun Animator.applyInterpolator(flourishAnimation: FlourishAnimation) {
    when (flourishAnimation) {
        FlourishAnimation.NORMAL -> this.interpolator = LinearInterpolator()
        FlourishAnimation.ACCELERATE -> this.interpolator = AccelerateInterpolator()
        FlourishAnimation.BOUNCE -> this.interpolator = BounceInterpolator()
        FlourishAnimation.OVERSHOOT -> this.interpolator = OvershootInterpolator()
    }
}