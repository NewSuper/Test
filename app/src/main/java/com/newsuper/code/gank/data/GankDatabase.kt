package com.newsuper.code.gank.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [GankDailyData::class],
version = 1,exportSchema = false)
@TypeConverters(value = [GankConvert::class])
abstract class GankDatabase :RoomDatabase(){
    abstract fun gankDao() :GankDao

    companion object{
        private var INSTANCE :GankDatabase?= null
        private val lock = Any()

        fun getInstance(context: Context):GankDatabase{
            synchronized(lock){
                if (INSTANCE == null){
                    INSTANCE =Room.databaseBuilder(context,
                    GankDatabase::class.java,
                    "gank.db").build()
                }
                return INSTANCE!!
            }
        }
    }
}