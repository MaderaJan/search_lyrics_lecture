package com.maderajan.muni.lyricsseach.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.maderajan.muni.lyricsseach.data.LyricsData

@Database(
    entities = [LyricsData::class],
    version = 1
)
abstract class LyricsSearchDatabase : RoomDatabase() {

    companion object {
        private const val NAME = "lyrics_search.db"

        fun create(context: Context): LyricsSearchDatabase =
            Room.databaseBuilder(context, LyricsSearchDatabase::class.java, NAME)
                .allowMainThreadQueries()
                .build()
    }

    abstract fun lyricsDataDao(): LyricsDataDao
}