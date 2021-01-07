package com.bt.base.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bt.base.BR
import java.util.concurrent.Executors

abstract class BaseRecyclerAdapter<Item, ViewBinding : ViewDataBinding>(callback: DiffUtil.ItemCallback<Item>) :
    ListAdapter<Item, BaseViewHolder<ViewBinding>>(
        AsyncDifferConfig.Builder<Item>(callback)
            .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
            .build()
    ) {

    var data = listOf<Item>()

    override fun submitList(list: MutableList<Item>?) {
        data = list ?: listOf()
        super.submitList(ArrayList<Item>(data))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ViewBinding> {
        return BaseViewHolder(
            DataBindingUtil.inflate<ViewBinding>(
                LayoutInflater.from(parent.context),
                getLayoutRes(viewType),
                parent,
                false
            ).apply {
                bindFirstTime(this)
            }
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding>, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            setVariable(BR.item, item)
            bindView(this, item, position)
            executePendingBindings()
        }
    }

    @LayoutRes
    abstract fun getLayoutRes(viewType: Int): Int

    protected open fun bindFirstTime(binding: ViewBinding) {}

    protected open fun bindView(binding: ViewBinding, item: Item, position: Int) {}
}

open class BaseViewHolder<ViewBinding : ViewDataBinding> constructor(val binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root)
