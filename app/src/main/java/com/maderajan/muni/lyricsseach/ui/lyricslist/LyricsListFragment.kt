package com.maderajan.muni.lyricsseach.ui.lyricslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.maderajan.muni.lyricsseach.databinding.FragmentLyricsListBinding
import com.maderajan.muni.lyricsseach.repository.LyricsRepository

class LyricsListFragment : Fragment() {

    private lateinit var binding: FragmentLyricsListBinding

    private val adapter = LyricsAdapter(
        onClick = { lyrics ->
            // TODO 10. (S) -> Zobrazit Toast, který vypíše { artist: songName }
        }
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLyricsListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO 5. LayoutManager
//        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        // TODO 6. Adapter -> Recycler
//        binding.recyclerView.adapter = adapter

        // TODO 6.1 Adapter submitList
        val lyricsRepository = LyricsRepository()
        adapter.submitList(lyricsRepository.getFakeLyrics())
    }
}