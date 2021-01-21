package com.bt.mp3.ui.discover

import androidx.recyclerview.widget.DiffUtil
import com.bt.base.ui.BaseRecyclerAdapter
import com.bt.mp3.R
import com.bt.mp3.databinding.ItemChildSectionPlaylistBinding
import com.bt.mp3.model.SongItem

class ChildSectionPlaylistAdapter : BaseRecyclerAdapter<SongItem, ItemChildSectionPlaylistBinding>(
    object : DiffUtil.ItemCallback<SongItem>() {
        override fun areContentsTheSame(oldItem: SongItem, newItem: SongItem): Boolean = oldItem == newItem

        override fun areItemsTheSame(oldItem: SongItem, newItem: SongItem): Boolean = oldItem.id == newItem.id
    }
) {

    override fun getLayoutRes(viewType: Int): Int = R.layout.item_child_section_playlist
}
