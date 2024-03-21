package com.maderajan.muni.lyricsseach.webservice.response

data class SearchResponse(
    val data: List<Data>,
) {

    data class Data(
        val id: String,
        val name: String,
        val picture_small: String
    )
}