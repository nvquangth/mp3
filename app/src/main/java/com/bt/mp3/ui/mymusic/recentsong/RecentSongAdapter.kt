package com.bt.mp3.ui.mymusic.recentsong

import androidx.recyclerview.widget.DiffUtil
import com.bt.base.ui.BaseRecyclerAdapter
import com.bt.mp3.R
import com.bt.mp3.databinding.ItemSongSimpleBinding
import com.bt.mp3.model.SongItem

class RecentSongAdapter(
    private val onItemClickListener: ((SongItem) -> Unit)? = null,
    private val onItemLongClickListener: ((SongItem) -> Unit)? = null
) : BaseRecyclerAdapter<SongItem, ItemSongSimpleBinding>(
    object : DiffUtil.ItemCallback<SongItem>() {

        override fun areContentsTheSame(oldItem: SongItem, newItem: SongItem): Boolean = oldItem == newItem

        override fun areItemsTheSame(oldItem: SongItem, newItem: SongItem): Boolean = oldItem.id == newItem.id
    }
) {
    override fun getLayoutRes(viewType: Int): Int = R.layout.item_song_simple

    override fun bindFirstTime(binding: ItemSongSimpleBinding) {
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
