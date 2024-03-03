package com.maderajan.muni.lyricsseach.data

// TODO 1. Lyrics Data
data class LyricsData(
    val id: String,
    val songName: String,
    val artistName: String,
    val coverUrl: String?,
    val lyrics: String,
    val isFavorite: Boolean
)