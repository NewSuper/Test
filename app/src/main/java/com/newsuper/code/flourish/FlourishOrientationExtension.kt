package com.newsuper.code.flourish


import android.view.View

internal fun FlourishOrientation.getPivotX(parent: View): Float {
    return when (this) {
        FlourishOrientation.TOP_LEFT -> 0f
        FlourishOrientation.TOP_RIGHT -> parent.width.toFloat()
        FlourishOrientation.BOTTOM_LEFT -> 0f
        FlourishOrientation.BOTTOM_RIGHT -> parent.width.toFloat()
    }
}

internal fun FlourishOrientation.getPivotY(parent: View): Float {
    return when (this) {
        FlourishOrientation.TOP_LEFT -> 0f
        FlourishOrientation.TOP_RIGHT -> 0f
        FlourishOrientation.BOTTOM_LEFT -> parent.height.toFloat()
        FlourishOrientation.BOTTOM_RIGHT -> parent.height.toFloat()
    }
}

internal fun FlourishOrientation.getRotation(): Float {
    return when (this) {
        FlourishOrientation.TOP_LEFT -> -90f
        FlourishOrientation.TOP_RIGHT -> 90f
        FlourishOrientation.BOTTOM_LEFT -> 90f
        FlourishOrientation.BOTTOM_RIGHT -> -90f
    }
}
