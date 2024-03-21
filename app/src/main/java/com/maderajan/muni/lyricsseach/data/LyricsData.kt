package com.maderajan.muni.lyricsseach.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

// TODO 1.1 Entity
@Entity
@Parcelize
data class LyricsData(
    @PrimaryKey
    val id: String,
    val songName: String,
    val artistName: String,
    val coverUrl: String?,
    val lyrics: String,
    val isFavorite: Boolean
): Parcelable