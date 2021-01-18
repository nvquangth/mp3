package com.bt.mp3.model

import android.os.Parcelable
import com.bt.base.model.ItemMapper
import com.bt.base.model.ModelItem
import com.bt.mp3.entity.Stream
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

@Parcelize
data class StreamItem(
    val v128: String? = null,
    val v320: String? = null
) : ModelItem(), Parcelable

class StreamItemMapper @Inject constructor() : ItemMapper<Stream, StreamItem> {
    override fun mapToPresentation(model: Stream): StreamItem = StreamItem(
        v128 = model.v128,
        v320 = model.v320
    )

    override fun mapToDomain(item: StreamItem): Stream = Stream(
        v128 = item.v128,
        v320 = item.v320
    )
}
