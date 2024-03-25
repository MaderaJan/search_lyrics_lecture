package com.maderajan.muni.lyricsseach.webservice

import com.maderajan.muni.lyricsseach.webservice.response.AlbumSongsResponse
import com.maderajan.muni.lyricsseach.webservice.response.ArtistAlbumsResponse
import com.maderajan.muni.lyricsseach.webservice.response.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DeezerWebService {

    @GET("search/artist")
    fun getArtistByName(
        @Query("q") artistName: String
    ): Call<SearchResponse>

    @GET("artist/{id}/albums")
    fun getAlbumsByArtistId(
        @Path("id") artistId: String
    ): Call<ArtistAlbumsResponse>

    @GET("album/{id}")
    fun getAlbumSongsById(
        @Path("id") albumId: String
    ): Call<AlbumSongsResponse>
}