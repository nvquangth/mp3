package com.bt.base.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bt.base.BR

/**
 * Not apply DiffUtil
 */
abstract class BaseRecyclerAdapter2<Item, ViewBinding : ViewDataBinding> : RecyclerView.Adapter<BaseRecyclerAdapter2.BaseViewHolder2<ViewBinding>>() {

    private var data = listOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder2<ViewBinding> {
        return BaseViewHolder2(
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

    override fun onBindViewHolder(holder: BaseViewHolder2<ViewBinding>, position: Int) {
        val item = data[position]
        holder.binding.apply {
            setVariable(BR.item, item)
            bindView(this, item, position)
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = data.size

    @LayoutRes
    abstract fun getLayoutRes(viewType: Int): Int

    protected open fun bindFirstTime(binding: ViewBinding) {}

    protected open fun bindView(binding: ViewBinding, item: Item, position: Int) {}

    fun setData(d: List<Item>) {
        data = d
        notifyDataSetChanged()
    }

    open class BaseViewHolder2<ViewBinding : ViewDataBinding> constructor(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)
}
