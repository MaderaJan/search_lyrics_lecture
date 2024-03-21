package com.maderajan.muni.lyricsseach.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maderajan.muni.lyricsseach.data.LyricsData

// TODO 1.2 Dao
@Dao
interface LyricsDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun persistLyricsData(lyricsData: LyricsData)

    @Query("SELECT * FROM LyricsData")
    fun selectAllLyrics(): List<LyricsData>

    @Query("DELETE FROM LyricsData WHERE id = :id")
    fun deleteLyricsData(id: String)
}