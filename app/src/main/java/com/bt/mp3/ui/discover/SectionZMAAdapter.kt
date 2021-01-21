package com.bt.mp3.ui.discover

import androidx.recyclerview.widget.DiffUtil
import com.bt.base.ui.BaseRecyclerAdapter
import com.bt.mp3.R
import com.bt.mp3.databinding.ItemSectionZmaBinding
import com.bt.mp3.model.SectionItem

class SectionZMAAdapter : BaseRecyclerAdapter<SectionItem, ItemSectionZmaBinding>(
    object : DiffUtil.ItemCallback<SectionItem>() {
        override fun areContentsTheSame(oldItem: SectionItem, newItem: SectionItem): Boolean = oldItem == newItem

        override fun areItemsTheSame(oldItem: SectionItem, newItem: SectionItem): Boolean = oldItem.sectionId == newItem.sectionId && oldItem.title == newItem.title
    }
) {

    override fun getLayoutRes(viewType: Int): Int = R.layout.item_section_zma

    override fun bindView(binding: ItemSectionZmaBinding, item: SectionItem, position: Int) {
        val zmaAdapter = ChildSectionZMAAdapter()
        binding.apply {
            recyclerSectionZma.apply {
                adapter = zmaAdapter
            }

            item.let {
                zmaAdapter.submitList(it.items?.toMutableList())
            }
        }
    }
}
