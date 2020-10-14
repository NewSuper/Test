package com.newsuper.code.gank.data

import android.text.TextUtils
import androidx.room.TypeConverter
import com.google.gson.Gson

class GankConvert {
    companion object {
        private val gson = Gson()

        @TypeConverter
        @JvmStatic
        fun toGankList(json: String): List<Gank> {
            if (TextUtils.isEmpty(json))
                return listOf()
            return gson.fromJson(json, Array<Gank>::class.java).asList()
        }

        @TypeConverter
        @JvmStatic
        fun toGankJsonStr(gankList: List<Gank>): String {
            return gson.toJson(gankList)
        }
    }
}