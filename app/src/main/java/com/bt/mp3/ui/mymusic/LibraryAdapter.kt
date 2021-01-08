package com.bt.mp3.ui.mymusic

import com.bt.base.ui.BaseRecyclerAdapter2
import com.bt.mp3.R
import com.bt.mp3.databinding.ItemLibraryBinding
import com.bt.mp3.model.LibraryItem

class LibraryAdapter(
    private val itemClickListener: ((LibraryItem) -> Unit)? = null
) : BaseRecyclerAdapter2<LibraryItem, ItemLibraryBinding>() {

    override fun getLayoutRes(viewType: Int): Int = R.layout.item_library

    override fun bindFirstTime(binding: ItemLibraryBinding) {
        binding.apply {
            root.setOnClickListener {
                item?.let {
                    itemClickListener?.invoke(it)
                }
            }
        }
    }
}
