package com.maderajan.muni.lyricsseach.repository

import android.content.Context
import com.maderajan.muni.lyricsseach.data.LyricsData
import com.maderajan.muni.lyricsseach.database.LyricsDataDao
import com.maderajan.muni.lyricsseach.database.LyricsSearchDatabase

class LyricsRepository(
    context: Context,
    private val lyricsDataDao: LyricsDataDao = LyricsSearchDatabase.create(context).lyricsDataDao()
) {

    fun insertOrDeleteLyrics(lyricsData: LyricsData): Boolean {
        if (lyricsData.isFavorite) {
            lyricsDataDao.deleteLyricsData(lyricsData.id)
        } else {
            lyricsDataDao.persistLyricsData(lyricsData.copy(isFavorite = true))
        }

        return !lyricsData.isFavorite
    }

    fun getAllLyrics(): List<LyricsData> =
        lyricsDataDao.selectAllLyrics()
}