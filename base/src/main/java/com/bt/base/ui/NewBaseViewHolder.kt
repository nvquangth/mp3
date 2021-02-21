package com.bt.base.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.annotation.MainThread
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bt.base.BR

// FIXME: should be abstract?
open class NewBaseViewHolder<in Item, Binding : ViewDataBinding>(
    parent: ViewGroup,
    @LayoutRes
    private val layoutId: Int,
    attachToParent: Boolean = false,
) : BaseRecyclerAdapter.BaseViewHolder<Binding>(
    DataBindingUtil.inflate<Binding>(
        LayoutInflater.from(parent.context),
        layoutId,
        parent,
        attachToParent,
    )
) {
    @CallSuper
    @MainThread
    open fun bind(item: Item) {
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }

    // TODO: Delete after migrating
    @Deprecated("This function will be deleted after transition", ReplaceWith("#bind(Item)"))
    fun bind(item: Item, apply: (Binding) -> Unit) {
        apply(binding)
        bind(item)
    }
}
