package com.newsuper.code.flourish


import android.view.ViewGroup

/** creates an instance of [Flourish] by [Flourish.Builder] using kotlin dsl. */
@FlourishDsl
inline fun <reified T : ViewGroup> createFlourish(parent: ViewGroup, block: Flourish.Builder<T>.() -> Unit): Flourish<T> =
        Flourish.Builder<T>(parent).apply(block).build()