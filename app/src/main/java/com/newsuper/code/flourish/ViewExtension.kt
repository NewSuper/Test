package com.newsuper.code.flourish


import android.view.View
import android.view.ViewGroup

/** sets visibility of a view. */
internal fun View.visible(visibility: Boolean) {
    if (visibility) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

/** applies [ViewGroup]'s layout params. */
internal fun ViewGroup.applyLayoutParams(block: ViewGroup.LayoutParams.() -> Unit) {
    layoutParams?.let {
        val params: ViewGroup.LayoutParams =
                (layoutParams as ViewGroup.LayoutParams).apply { block(this) }
        layoutParams = params
    }
}