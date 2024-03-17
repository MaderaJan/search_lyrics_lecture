package com.maderajan.muni.lyricsseach.ui.search

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.maderajan.muni.lyricsseach.repository.SearchRepository

class SearchBottomSheet : BottomSheetDialogFragment() {

    // TODO 3.1 (S) Vytvořit a předat binding
    // TODO 3.2 (S) Odokomentovat
//    private val args: SearchBottomSheetArgs by navArgs()

    private val searchRepository: SearchRepository by lazy {
        SearchRepository()
    }

    // TODO 4.1 (S) Vytvořit Adapter
    private val adapter = SearchResultAdapter { result ->
        // TODO 5. (S) Odokomentovat - vrácení result do předešlého fragmentu
//        setFragmentResult(args.searchType.name, bundleOf(SEARCH_KEY to result))
        findNavController().navigateUp()
    }

    companion object {
        const val SEARCH_KEY = "search_result"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO 4.2 (S) Přidat adapter do recycleru, přidat layout manager do recycleru

        // TODO 3.3 (S) Odkomentovat
//        val showSearchArea = args.searchType == SearchType.ARTIST
//        binding.searchQueryEditText.isVisible = showSearchArea

        // TODO 3.4 (S) Odkomentovat
//        if (args.searchType == SearchType.ALBUM || args.searchType == SearchType.SONGS) {
//            search()
//        }

        // TODO 3.5 (S) Odkomentovat
//        binding.searchButton.setOnClickListener {
//            search()
//        }
    }

    private fun search() {
        // TODO 4.3 (S) SearchRepository.fakeSearch
        // TODO 4.4 (S) Zobrazit a schovat binding.searchButtonProgressBar
    }
}