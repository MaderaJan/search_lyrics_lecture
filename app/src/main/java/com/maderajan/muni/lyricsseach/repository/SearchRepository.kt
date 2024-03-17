package com.maderajan.muni.lyricsseach.repository

import android.os.Handler
import android.os.Looper
import com.maderajan.muni.lyricsseach.data.SearchResult
import com.maderajan.muni.lyricsseach.data.SearchType

class SearchRepository {

    fun fakeSearch(type: SearchType, success: (List<SearchResult>) -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            val result = mutableListOf<SearchResult>()

            repeat(10) {
                result.add(
                    SearchResult(
                        id = it.toString(),
                        name = "$type - $it",
                        imageUrl = "https://static-00.iconduck.com/assets.00/search-icon-2044x2048-psdrpqwp.png"
                    )
                )
            }

            success(result)
        }, 1000)
    }
}