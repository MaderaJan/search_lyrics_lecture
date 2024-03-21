package com.maderajan.muni.lyricsseach.webservice.response

data class ArtistAlbumsResponse(
    val data: List<Data>,
) {

    data class Data(
        val id: String,
        val title: String,
        val cover_small: String
    )
}