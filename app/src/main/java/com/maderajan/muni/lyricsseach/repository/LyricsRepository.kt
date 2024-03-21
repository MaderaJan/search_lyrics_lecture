package com.maderajan.muni.lyricsseach.repository

import android.content.Context
import com.maderajan.muni.lyricsseach.data.LyricsData
import com.maderajan.muni.lyricsseach.database.LyricsDataDao
import com.maderajan.muni.lyricsseach.database.LyricsSearchDatabase

// TODO 4.1 Inicializace DB
class LyricsRepository(
    context: Context,
    private val lyricsDataDao: LyricsDataDao = LyricsSearchDatabase.create(context).lyricsDataDao()
) {

    // TODO 4.3 getAllLyrics v LyricsListFragmentu
    fun insertOrDeleteLyrics(lyricsData: LyricsData): Boolean {
        if (lyricsData.isFavorite) {
            lyricsDataDao.deleteLyricsData(lyricsData.id)
        } else {
            lyricsDataDao.persistLyricsData(lyricsData.copy(isFavorite = true))
        }

        return !lyricsData.isFavorite
    }

    // TODO 4.2 getAllLyrics v LyricsListFragmentu
    fun getAllLyrics(): List<LyricsData> =
        lyricsDataDao.selectAllLyrics()
}