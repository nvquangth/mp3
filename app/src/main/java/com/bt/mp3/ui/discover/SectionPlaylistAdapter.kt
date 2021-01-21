package com.bt.mp3.ui.discover

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bt.base.ui.BaseRecyclerAdapter
import com.bt.mp3.R
import com.bt.mp3.databinding.ItemSectionPlaylistBinding
import com.bt.mp3.model.SectionItem

class SectionPlaylistAdapter : BaseRecyclerAdapter<SectionItem, ItemSectionPlaylistBinding>(
    object : DiffUtil.ItemCallback<SectionItem>() {
        override fun areContentsTheSame(oldItem: SectionItem, newItem: SectionItem): Boolean = oldItem == newItem

        override fun areItemsTheSame(oldItem: SectionItem, newItem: SectionItem): Boolean = oldItem.sectionId == newItem.sectionId && oldItem.title == newItem.title
    }
) {

    override fun getLayoutRes(viewType: Int): Int = R.layout.item_section_playlist

    override fun bindView(binding: ItemSectionPlaylistBinding, item: SectionItem, position: Int) {
        val playlistAdapter = ChildSectionPlaylistAdapter()

        binding.apply {
            recyclerSectionPlaylist.apply {
                adapter = playlistAdapter
                addItemDecoration(
                    object : RecyclerView.ItemDecoration() {
                        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                            super.getItemOffsets(outRect, view, parent, state)
                            val pos = parent.getChildAdapterPosition(view)
                            if (pos == 0) {
                                outRect.apply {
                                    left = context.resources.getDimensionPixelOffset(R.dimen.dp_12)
                                }
                            }
                            outRect.apply {
                                right = context.resources.getDimensionPixelOffset(R.dimen.dp_12)
                            }
                        }
                    }
                )
            }

            item.let {
                playlistAdapter.submitList(it.items?.toMutableList())
            }
        }
    }
}
