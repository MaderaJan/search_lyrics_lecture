package com.maderajan.muni.lyricsseach.ui.lyricslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maderajan.muni.lyricsseach.data.LyricsData
import com.maderajan.muni.lyricsseach.databinding.ItemListBinding

// TODO 2. Adapter
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

// TODO 3. ViewHolder
class LyricsViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: LyricsData, onClick: (LyricsData) -> Unit) {
        // TODO 8. (S) bindování dat do view
            // - (hint) obrázek -> coverImageView (použití coil)
            // - (hint) bind songName a artistName (view.text) -> songNameTextView, artistNameTextView
            // - (hint) click callback (onClick(item)) -> binding.root.setOnClickListener

        //  TODO 8.1 (S) kód pro získání obráku z URL (stačí odkomentovat)
//        binding.coverImageView.load(item.coverUrl) {
//            crossfade(true)
//            placeholder(R.drawable.ic_image_placeholder)
//            transformations(RoundedCornersTransformation(16f))
//        }
    }
}

// TODO 4. DiffUtil
class LyricsDiffUtil : DiffUtil.ItemCallback<LyricsData>() {

    override fun areItemsTheSame(oldItem: LyricsData, newItem: LyricsData): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: LyricsData, newItem: LyricsData): Boolean =
        oldItem == newItem
}