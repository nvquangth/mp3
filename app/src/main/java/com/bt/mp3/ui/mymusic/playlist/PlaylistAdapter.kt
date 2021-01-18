package com.bt.mp3.ui.mymusic.playlist

import androidx.recyclerview.widget.DiffUtil
import com.bt.base.ui.BaseRecyclerAdapter
import com.bt.mp3.R
import com.bt.mp3.databinding.ItemPlaylistSuggestionBinding
import com.bt.mp3.model.SongItem

class PlaylistAdapter(
    private val onItemClickListener: ((SongItem) -> Unit)? = null,
    private val onItemLongClickListener: ((SongItem) -> Unit)? = null,
    private val onFavoriteItemClickListener: ((SongItem) -> Unit)? = null
) : BaseRecyclerAdapter<SongItem, ItemPlaylistSuggestionBinding>(
    object : DiffUtil.ItemCallback<SongItem>() {

        override fun areContentsTheSame(oldItem: SongItem, newItem: SongItem): Boolean = oldItem == newItem

        override fun areItemsTheSame(oldItem: SongItem, newItem: SongItem): Boolean = oldItem.id == newItem.id
    }
) {
    override fun getLayoutRes(viewType: Int): Int = R.layout.item_playlist_suggestion

    override fun bindFirstTime(binding: ItemPlaylistSuggestionBinding) {
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
            imageFavorite.setOnClickListener {
                item?.let {
                    onFavoriteItemClickListener?.invoke(it)
                }
            }
        }
    }
}
