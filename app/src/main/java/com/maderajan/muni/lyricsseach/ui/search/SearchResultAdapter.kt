package com.maderajan.muni.lyricsseach.ui.search

import com.maderajan.muni.lyricsseach.data.SearchResult

class SearchResultAdapter(
    private val onClick: (SearchResult) -> Unit
)

// TODO 4.2 (S) Vytvořit Adapter, ViewHolder, Diffutil
// TODO 4.3 (S) ViewHolder bude používat item_list.xml
// ListAdapter<SearchResult, SearchResultViewHolder>(SearchResultDiffUtil())