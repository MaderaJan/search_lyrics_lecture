package com.maderajan.muni.lyricsseach.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.maderajan.muni.lyricsseach.R
import com.maderajan.muni.lyricsseach.data.SearchResult
import com.maderajan.muni.lyricsseach.databinding.ItemListBinding

class SearchResultAdapter(
    private val onClick: (SearchResult) -> Unit
) : ListAdapter<SearchResult, SearchResultViewHolder>(SearchResultDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder =
        SearchResultViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onClick)
    }
}

class SearchResultViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SearchResult, onClick: (SearchResult) -> Unit) {
        binding.coverImageView.isVisible = item.imageUrl != null

        binding.coverImageView.load(item.imageUrl) {
            crossfade(true)
            placeholder(R.drawable.ic_image_placeholder)
            transformations(RoundedCornersTransformation(16f))
        }

        binding.songNameTextView.text = item.name
        binding.artistNameTextView.isVisible = false

        binding.root.setOnClickListener {
            onClick(item)
        }
    }
}

class SearchResultDiffUtil : DiffUtil.ItemCallback<SearchResult>() {

    override fun areItemsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean =
        oldItem == newItem
}