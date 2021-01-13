package com.bt.mp3.ui.mymusic.playlist

import androidx.recyclerview.widget.DiffUtil
import com.bt.base.ui.BaseRecyclerAdapter
import com.bt.mp3.R
import com.bt.mp3.databinding.ItemPlaylistSuggestionBinding
import com.bt.mp3.model.PlaylistItem

class PlaylistAdapter(
    private val onItemClickListener: ((PlaylistItem) -> Unit)? = null,
    private val onItemLongClickListener: ((PlaylistItem) -> Unit)? = null,
    private val onFavoriteItemClickListener: ((PlaylistItem) -> Unit)? = null
) : BaseRecyclerAdapter<PlaylistItem, ItemPlaylistSuggestionBinding>(
    object : DiffUtil.ItemCallback<PlaylistItem>() {

        override fun areContentsTheSame(oldItem: PlaylistItem, newItem: PlaylistItem): Boolean = oldItem.id == newItem.id &&
            oldItem.title == newItem.title &&
            oldItem.publisher == newItem.publisher &&
            oldItem.type == newItem.type

        override fun areItemsTheSame(oldItem: PlaylistItem, newItem: PlaylistItem): Boolean = oldItem.id == newItem.id
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
