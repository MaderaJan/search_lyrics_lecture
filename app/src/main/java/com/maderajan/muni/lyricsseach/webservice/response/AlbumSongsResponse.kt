package com.maderajan.muni.lyricsseach.webservice.response

data class AlbumSongsResponse(
    val tracks: Tracks,
) {

    data class Tracks(
        val data: List<Data>
    ) {
        data class Data(
            val id: String,
            val title: String,
        )
    }
}