package com.maderajan.muni.lyricsseach.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchResult(
    val id: String,
    val name: String,
    val imageUrl: String?
): Parcelable