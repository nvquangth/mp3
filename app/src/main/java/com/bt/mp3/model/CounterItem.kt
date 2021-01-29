package com.bt.mp3.model

import android.os.Parcelable
import com.bt.base.model.ItemMapper
import com.bt.base.model.ModelItem
import com.bt.mp3.entity.Counter
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CounterItem(
    val time: Long? = null,
    val hour: String? = null,
    val counter: Long? = null
) : ModelItem(), Parcelable

class CounterItemMapper : ItemMapper<Counter, CounterItem> {
    override fun mapToPresentation(model: Counter): CounterItem = CounterItem(
        time = model.time,
        hour = model.hour,
        counter = model.counter
    )

    override fun mapToDomain(item: CounterItem): Counter = Counter(
        time = item.time,
        hour = item.hour,
        counter = item.counter
    )
}
