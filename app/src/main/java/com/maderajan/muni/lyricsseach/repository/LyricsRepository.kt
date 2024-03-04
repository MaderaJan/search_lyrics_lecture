package com.maderajan.muni.lyricsseach.repository

import com.maderajan.muni.lyricsseach.data.LyricsData

class LyricsRepository {

    fun getFakeLyrics(): List<LyricsData> {
        val fakeData = mutableListOf<LyricsData>()

        repeat(100) {
            fakeData.add(
                LyricsData(
                    id = it.toString(),
                    songName = "Song - $it",
                    artistName = "Artist - $it",
                    coverUrl = "https://www.theaudiodb.com/images/media/album/cdart/disintegration-4e60244b4b6d6.png",
                    lyrics = "",
                    isFavorite = false,
                )
            )
        }

        return fakeData
    }
}