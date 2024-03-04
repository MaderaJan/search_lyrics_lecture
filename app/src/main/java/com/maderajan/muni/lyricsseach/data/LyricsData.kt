package com.maderajan.muni.lyricsseach.data

data class LyricsData(
    val id: String,
    val songName: String,
    val artistName: String,
    val coverUrl: String?,
    val lyrics: String,
    val isFavorite: Boolean
)