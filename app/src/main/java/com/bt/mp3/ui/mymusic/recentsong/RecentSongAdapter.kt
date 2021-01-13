package com.bt.mp3.ui.mymusic.recentsong

import androidx.recyclerview.widget.DiffUtil
import com.bt.base.ui.BaseRecyclerAdapter
import com.bt.mp3.R
import com.bt.mp3.databinding.ItemRecentSongBinding
import com.bt.mp3.model.SongItem

class RecentSongAdapter(
    private val onItemClickListener: ((SongItem) -> Unit)? = null,
    private val onItemLongClickListener: ((SongItem) -> Unit)? = null
) : BaseRecyclerAdapter<SongItem, ItemRecentSongBinding>(
    object : DiffUtil.ItemCallback<SongItem>() {

        override fun areContentsTheSame(oldItem: SongItem, newItem: SongItem): Boolean = oldItem == newItem

        override fun areItemsTheSame(oldItem: SongItem, newItem: SongItem): Boolean = oldItem.id == newItem.id
    }
) {
    override fun getLayoutRes(viewType: Int): Int = R.layout.item_recent_song

    override fun bindFirstTime(binding: ItemRecentSongBinding) {
        binding.apply {
            root.setOnClickListener {
                item?.let {
                    onItemClickListener?.invoke(it)
                }
            }

            root.setOnLongClickListener {
                item?.let {
                    onItemLongClickListener?.invoke(it)
                }
                true
            }
        }
    }
}
