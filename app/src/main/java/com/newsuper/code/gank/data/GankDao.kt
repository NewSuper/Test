package com.newsuper.code.gank.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.newsuper.code.gank.util.getDateString
import java.util.*

@Dao
interface GankDao {
    @Query("SELECT * FROM daily WHERE _date = :date")
    fun loadDaily(date: String = Date().getDateString()): GankDailyData?

    @Query("DELETE FROM daily")
    fun deleteDaily()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(daily: GankDailyData)
}