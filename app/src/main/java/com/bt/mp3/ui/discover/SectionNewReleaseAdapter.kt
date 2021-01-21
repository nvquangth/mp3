package com.bt.mp3.ui.discover

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bt.base.ui.BaseRecyclerAdapter
import com.bt.mp3.R
import com.bt.mp3.databinding.ItemSectionNewReleaseBinding
import com.bt.mp3.model.SectionItem

class SectionNewReleaseAdapter : BaseRecyclerAdapter<SectionItem, ItemSectionNewReleaseBinding>(
    object : DiffUtil.ItemCallback<SectionItem>() {
        override fun areContentsTheSame(oldItem: SectionItem, newItem: SectionItem): Boolean = oldItem == newItem

        override fun areItemsTheSame(oldItem: SectionItem, newItem: SectionItem): Boolean = oldItem.sectionId == newItem.sectionId && oldItem.sectionType == newItem.sectionType
    }
) {

    override fun getLayoutRes(viewType: Int): Int = R.layout.item_section_new_release

    override fun bindView(binding: ItemSectionNewReleaseBinding, item: SectionItem, position: Int) {
        val songAdapter = ChildSectionNewReleaseAdapter()

        binding.apply {
            recyclerSectionNewRelease.apply {
                adapter = songAdapter

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
                songAdapter.submitList(it.items?.toMutableList())
            }
        }
    }
}
