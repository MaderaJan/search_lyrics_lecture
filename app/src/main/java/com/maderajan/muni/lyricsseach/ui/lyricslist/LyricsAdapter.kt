package com.maderajan.muni.lyricsseach.ui.lyricslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.maderajan.muni.lyricsseach.R
import com.maderajan.muni.lyricsseach.data.LyricsData
import com.maderajan.muni.lyricsseach.databinding.ItemListBinding

class LyricsAdapter(
    private val onClick: (LyricsData) -> Unit
) : ListAdapter<LyricsData, LyricsViewHolder>(LyricsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LyricsViewHolder =
        LyricsViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: LyricsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onClick)
    }
}

class LyricsViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: LyricsData, onClick: (LyricsData) -> Unit) {
        binding.coverImageView.load(item.coverUrl) {
            crossfade(true)
            placeholder(R.drawable.ic_image_placeholder)
            transformations(RoundedCornersTransformation(16f))
        }

        binding.songNameTextView.text = item.songName
        binding.artistNameTextView.text = item.artistName

        binding.root.setOnClickListener {
            onClick(item)
        }
    }
}

class LyricsDiffUtil : DiffUtil.ItemCallback<LyricsData>() {

    override fun areItemsTheSame(oldItem: LyricsData, newItem: LyricsData): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: LyricsData, newItem: LyricsData): Boolean =
        oldItem == newItem
}