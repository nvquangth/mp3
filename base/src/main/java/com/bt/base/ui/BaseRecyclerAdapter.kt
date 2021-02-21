package com.bt.base.ui

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.Executors

/**
 * Apply DiffUtil
 */
abstract class BaseRecyclerAdapter<Item, ViewBinding : ViewDataBinding>(callback: DiffUtil.ItemCallback<Item>) :
    ListAdapter<Item, BaseRecyclerAdapter.BaseViewHolder<ViewBinding>>(
        AsyncDifferConfig.Builder<Item>(callback)
            .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
            .build()
    ) {

    // not thread-safe
    private var cachedListRef: List<Item>? = null

    override fun submitList(list: MutableList<Item>?) {
        this.submitList(list, null)
    }

    override fun submitList(list: MutableList<Item>?, commitCallback: Runnable?) {
        // sửa lỗi khi dùng adapter kết hợp với Flow hoặc LiveData emit phiên giá trị mới nhưng cùng
        // tham chiếu
        if (list === cachedListRef) {
            notifyDataSetChanged()
        } else {
            super.submitList(list, commitCallback)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewBaseViewHolder<Item, ViewBinding> {
        return NewBaseViewHolder<Item, ViewBinding>(parent, getLayoutRes(viewType))
            // TODO: Delete after migration
            .apply { bindFirstTime(binding) }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding>, position: Int) {
        val item = getItem(position)
        // TODO: use NewBaseViewHolder#bind(Item) after migration
        // holder.bind(item)
        @Suppress("UNCHECKED_CAST")
        (holder as? NewBaseViewHolder<Item, ViewBinding>)?.bind(item) {
            bindView(
                it,
                item,
                position
            )
        }
    }

    // TODO: Delete after migration
    @Deprecated("")
    @LayoutRes
    abstract fun getLayoutRes(viewType: Int): Int

    // TODO: Delete after migration
    @Deprecated(
        "Adapter is responsible for ViewHolder's construction",
        ReplaceWith("BaseViewHolder(ViewGroup)")
    )
    protected open fun bindFirstTime(binding: ViewBinding) {
    }

    // TODO: Delete after migration
    @Deprecated(
        "Adapter is not responsible for binding new item logic of ViewHolder",
        ReplaceWith("BaseViewHolder#bind(Item)")
    )
    protected open fun bindView(binding: ViewBinding, item: Item, position: Int) {
    }

    // TODO: Migrate to NewBaseViewModel
    // this class now acts as a transition class when migrating, because there
    // maybe subclasses of this class in other commits which may cause code conflicts
    @Deprecated("")
    open class BaseViewHolder<ViewBinding : ViewDataBinding>
    @Deprecated("ViewHolder decide how to create itself, will be replaced by another constructor")
    constructor(
        @get:Deprecated("This property should be protected")
        val binding: ViewBinding,
    ) : RecyclerView.ViewHolder(binding.root)
}
