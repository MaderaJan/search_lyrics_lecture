package com.maderajan.muni.lyricsseach.repository

import com.maderajan.muni.lyricsseach.data.SearchResult
import com.maderajan.muni.lyricsseach.data.SearchType
import com.maderajan.muni.lyricsseach.webservice.DeezerWebService
import com.maderajan.muni.lyricsseach.webservice.LyricsWebService
import com.maderajan.muni.lyricsseach.webservice.RetrofitUtil
import com.maderajan.muni.lyricsseach.webservice.response.AlbumSongsResponse
import com.maderajan.muni.lyricsseach.webservice.response.ArtistAlbumsResponse
import com.maderajan.muni.lyricsseach.webservice.response.LyricsResponse
import com.maderajan.muni.lyricsseach.webservice.response.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchRepository(
    private val lyricsWebService: LyricsWebService = RetrofitUtil.createLyricsService(),
    private val deezerWebService: DeezerWebService = RetrofitUtil.createDeezerService()
) {

    fun search(type: SearchType, query: String, success: (List<SearchResult>) -> Unit, fail: () -> Unit) {
        when(type) {
            SearchType.ARTIST -> searchArtist(query, success, fail)
            SearchType.ALBUM -> searchAlbum(query, success, fail)
            SearchType.SONGS -> searchSong(query, success, fail)
        }
    }

    private fun searchArtist(name: String, success: (List<SearchResult>) -> Unit, fail: () -> Unit) {
        deezerWebService.getArtistByName(name)
            .enqueue(object : Callback<SearchResponse> {
                override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                    if (response.isSuccessful) {
                        val searchResponse = response.body()

                        val result = searchResponse?.data?.map {
                            SearchResult(id = it.id, name = it.name, imageUrl = it.picture_small)
                        } ?: emptyList()

                        success(result)
                    } else {
                        fail()
                    }
                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    fail()
                }
            })
    }

    private fun searchAlbum(artistId: String, success: (List<SearchResult>) -> Unit, fail: () -> Unit) {
        deezerWebService.getAlbumsByArtistId(artistId)
            .enqueue(object : Callback<ArtistAlbumsResponse> {
                override fun onResponse(call: Call<ArtistAlbumsResponse>, response: Response<ArtistAlbumsResponse>) {
                    if (response.isSuccessful) {
                        val searchResponse = response.body()

                        val result = searchResponse?.data?.map {
                            SearchResult(id = it.id, name = it.title, imageUrl = it.cover_small)
                        } ?: emptyList()

                        success(result)
                    } else {
                        fail()
                    }
                }

                override fun onFailure(call: Call<ArtistAlbumsResponse>, t: Throwable) {
                    fail()
                }
            })
    }

    private fun searchSong(albumId: String, success: (List<SearchResult>) -> Unit, fail: () -> Unit) {
        deezerWebService.getAlbumSongsById(albumId)
            .enqueue(object : Callback<AlbumSongsResponse> {
                override fun onResponse(call: Call<AlbumSongsResponse>, response: Response<AlbumSongsResponse>) {
                    if (response.isSuccessful) {
                        val searchResponse = response.body()

                        val result = searchResponse?.tracks?.data?.map {
                            SearchResult(id = it.id, name = it.title, imageUrl = null)
                        } ?: emptyList()

                        success(result)
                    } else {
                        fail()
                    }
                }

                override fun onFailure(call: Call<AlbumSongsResponse>, t: Throwable) {
                    fail()
                }
            })
    }

    fun searchLyrics(artistName: String, songName: String, success: (String?) -> Unit, fail: () -> Unit) {
        lyricsWebService.getLyricsByArtistNameAndSongName(artistName, songName)
            .enqueue(object : Callback<LyricsResponse> {
                override fun onResponse(call: Call<LyricsResponse>, response: Response<LyricsResponse>) {
                    if (response.isSuccessful) {
                        val searchResponse = response.body()
                        success(searchResponse?.lyrics)
                    } else {
                        fail()
                    }
                }

                override fun onFailure(call: Call<LyricsResponse>, t: Throwable) {
                    fail()
                }
            })
    }
}