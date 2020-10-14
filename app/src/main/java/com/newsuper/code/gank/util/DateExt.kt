package com.newsuper.code.gank.util

import java.text.SimpleDateFormat
import java.util.*

fun Date.getDateString():String{
    val  sdf = SimpleDateFormat("yyyy-MM-dd",Locale.CHINA)
    return sdf.format(this)
}