package com.maderajan.muni.lyricsseach.ui.lyricslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.maderajan.muni.lyricsseach.databinding.FragmentLyricsListBinding
import com.maderajan.muni.lyricsseach.repository.LyricsRepository

class LyricsListFragment : Fragment() {

    private lateinit var binding: FragmentLyricsListBinding

    private val adapter = LyricsAdapter(
        onClick = { lyrics ->
            Toast.makeText(context, "${lyrics.artistName} + ${lyrics.songName}", Toast.LENGTH_LONG)
                .show()
        }
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLyricsListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        val lyricsRepository = LyricsRepository()
        adapter.submitList(lyricsRepository.getFakeLyrics())
    }
}