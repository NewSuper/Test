package com.newsuper.code.flourish


import android.graphics.drawable.Drawable

data class FeedItem(
        val profile: Drawable?,
        val title: String,
        val content: String
)