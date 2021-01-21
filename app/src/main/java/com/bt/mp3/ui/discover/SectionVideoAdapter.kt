package com.bt.mp3.ui.discover

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bt.base.ui.BaseRecyclerAdapter
import com.bt.mp3.R
import com.bt.mp3.databinding.ItemSectionVideoBinding
import com.bt.mp3.model.SectionItem

class SectionVideoAdapter : BaseRecyclerAdapter<SectionItem, ItemSectionVideoBinding>(
    object : DiffUtil.ItemCallback<SectionItem>() {
        override fun areContentsTheSame(oldItem: SectionItem, newItem: SectionItem): Boolean = oldItem == newItem

        override fun areItemsTheSame(oldItem: SectionItem, newItem: SectionItem): Boolean = oldItem.sectionId == newItem.sectionId && oldItem.title == newItem.title
    }
) {

    override fun getLayoutRes(viewType: Int): Int = R.layout.item_section_video

    override fun bindView(binding: ItemSectionVideoBinding, item: SectionItem, position: Int) {
        val videoAdapter = ChildSectionVideoAdapter()

        binding.apply {
            recyclerSectionVideo.apply {
                adapter = videoAdapter

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
                videoAdapter.submitList(it.items?.toMutableList())
            }
        }
    }
}
