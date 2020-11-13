package com.newsuper.code.flourish


/** Interface definition for a callback to be invoked when a [Flourish] is showed or dismissed. */
interface FlourishListener {
    /** invoked when the flourish layout is showed or dismissed. */
    fun onChanged(isShowing: Boolean)
}
