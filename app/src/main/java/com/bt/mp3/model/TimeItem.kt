package com.bt.mp3.model

import android.os.Parcelable
import com.bt.base.model.ItemMapper
import com.bt.base.model.ModelItem
import com.bt.mp3.entity.Time
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TimeItem(
    val hour: String? = null
) : ModelItem(), Parcelable

class TimeItemMapper : ItemMapper<Time, TimeItem> {
    override fun mapToPresentation(model: Time): TimeItem = TimeItem(
        hour = model.hour
    )

    override fun mapToDomain(item: TimeItem): Time = Time(
        hour = item.hour
    )
}
