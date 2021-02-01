package com.bt.mp3.ui.chart

import androidx.recyclerview.widget.DiffUtil
import com.bt.base.ui.BaseRecyclerAdapter
import com.bt.mp3.R
import com.bt.mp3.databinding.ItemSongChartBinding
import com.bt.mp3.model.SongItem

class SongChartAdapter : BaseRecyclerAdapter<SongItem, ItemSongChartBinding>(
    object : DiffUtil.ItemCallback<SongItem>() {
        override fun areContentsTheSame(oldItem: SongItem, newItem: SongItem): Boolean = oldItem == newItem

        override fun areItemsTheSame(oldItem: SongItem, newItem: SongItem): Boolean = oldItem.id == newItem.id
    }
) {

    override fun getLayoutRes(viewType: Int): Int = R.layout.item_song_chart

    override fun bindFirstTime(binding: ItemSongChartBinding) {
        binding.apply {
            root.setOnClickListener {
            }

            imageMore.setOnClickListener {
            }
        }
    }
}
