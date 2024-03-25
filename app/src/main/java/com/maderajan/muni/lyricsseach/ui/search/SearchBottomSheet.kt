package com.maderajan.muni.lyricsseach.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.maderajan.muni.lyricsseach.R
import com.maderajan.muni.lyricsseach.data.SearchType
import com.maderajan.muni.lyricsseach.databinding.BottomSheetSearchBinding
import com.maderajan.muni.lyricsseach.repository.SearchRepository

class SearchBottomSheet : BottomSheetDialogFragment() {

    private val args: SearchBottomSheetArgs by navArgs()
    private lateinit var binding: BottomSheetSearchBinding

    private val searchRepository: SearchRepository by lazy {
        SearchRepository()
    }

    private val adapter = SearchResultAdapter { result ->
        setFragmentResult(args.searchType.name, bundleOf(SEARCH_KEY to result))
        findNavController().navigateUp()
    }

    companion object {
        const val SEARCH_KEY = "search_result"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = BottomSheetSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        val showSearchArea = args.searchType == SearchType.ARTIST
        binding.searchQueryEditText.isVisible = showSearchArea

        if (args.searchType == SearchType.ALBUM || args.searchType == SearchType.SONGS) {
            search()
        }

        binding.searchButton.setOnClickListener {
            search()
        }
    }

    private fun search() {
        toggleSearch(showProgressBar = true)

        val query = when (args.searchType) {
            SearchType.ARTIST -> binding.searchQueryEditText.text.toString()
            SearchType.ALBUM, SearchType.SONGS -> args.query.orEmpty()
        }

        searchRepository.search(type = args.searchType, query = query,
            success = {
                adapter.submitList(it)
                toggleSearch(showProgressBar = false)
            }, fail = {
                Toast.makeText(requireContext(), getString(R.string.nothing_was_found), Toast.LENGTH_SHORT).show()
                toggleSearch(showProgressBar = false)
            })
    }

    private fun toggleSearch(showProgressBar: Boolean) {
        binding.searchButtonProgressBar.isVisible = showProgressBar
        binding.searchButton.isInvisible = showProgressBar
    }
}