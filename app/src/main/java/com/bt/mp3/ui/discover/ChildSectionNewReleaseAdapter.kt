package com.bt.mp3.ui.discover

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.bt.base.ui.BaseRecyclerAdapter
import com.bt.mp3.R
import com.bt.mp3.databinding.ItemChildSectionNewReleaseBinding
import com.bt.mp3.model.SongItem

class ChildSectionNewReleaseAdapter : BaseRecyclerAdapter<SongItem, ItemChildSectionNewReleaseBinding>(
    object : DiffUtil.ItemCallback<SongItem>() {
        override fun areContentsTheSame(oldItem: SongItem, newItem: SongItem): Boolean = oldItem == newItem

        override fun areItemsTheSame(oldItem: SongItem, newItem: SongItem): Boolean = oldItem.id == newItem.id
    }
) {

    override fun getLayoutRes(viewType: Int): Int = R.layout.item_child_section_new_release

    override fun bindFirstTime(binding: ItemChildSectionNewReleaseBinding) {
        binding.apply {
            root.setOnClickListener {
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun bindView(binding: ItemChildSectionNewReleaseBinding, item: SongItem, position: Int) {
        binding.apply {
            textRanking.text = "#${position + 1}"
        }
    }
}
