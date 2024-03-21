package com.maderajan.muni.lyricsseach.repository

import com.maderajan.muni.lyricsseach.data.SearchResult
import com.maderajan.muni.lyricsseach.data.SearchType
import com.maderajan.muni.lyricsseach.webservice.DeezerWebService
import com.maderajan.muni.lyricsseach.webservice.LyricsWebService
import com.maderajan.muni.lyricsseach.webservice.RetrofitUtil
import com.maderajan.muni.lyricsseach.webservice.response.LyricsResponse
import com.maderajan.muni.lyricsseach.webservice.response.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO 3.3 Inicializace REST API
class SearchRepository(
    private val lyricsWebService: LyricsWebService = RetrofitUtil.createLyricsService(),
    private val deezerWebService: DeezerWebService = RetrofitUtil.createDeezerService()
) {

    // TODO 3.2 Search change
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
        // TODO 5.1 (S) Search Album z API
        // TODO 5.1 (S) deezerWebService.getAlbumsByArtistId(artistId)
    }

    private fun searchSong(albumId: String, success: (List<SearchResult>) -> Unit, fail: () -> Unit) {
        // TODO 5.2 (S) Search Song z API
        // TODO 5.2 (S) deezerWebService.getAlbumSongsById(albumId)
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