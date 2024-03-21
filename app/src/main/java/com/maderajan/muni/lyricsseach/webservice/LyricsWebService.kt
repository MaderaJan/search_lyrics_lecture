package com.maderajan.muni.lyricsseach.webservice

import com.maderajan.muni.lyricsseach.webservice.response.LyricsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

// TODO 2.2 LyricsWebService
interface LyricsWebService {

    @GET("{artistName}/{songName}")
    fun getLyricsByArtistNameAndSongName(
        @Path("artistName") artistName: String,
        @Path("songName") songName: String,
    ): Call<LyricsResponse>
}