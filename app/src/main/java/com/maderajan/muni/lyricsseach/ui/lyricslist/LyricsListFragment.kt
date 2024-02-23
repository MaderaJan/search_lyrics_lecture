package com.maderajan.muni.lyricsseach.ui.lyricslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.maderajan.muni.lyricsseach.databinding.FragmentLyricsListBinding

class LyricsListFragment : Fragment() {

    // TODO 6. Fragment binding + lateinit usage
    private lateinit var binding: FragmentLyricsListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLyricsListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}