package com.bt.mp3.model

import android.os.Parcelable
import com.bt.base.model.ItemMapper
import com.bt.base.model.ModelItem
import com.bt.mp3.entity.Chart
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

@Parcelize
data class ChartItem(
    val minScore: Float? = null,
    val maxScore: Float? = null,
    val totalScore: Long? = null,
    val times: List<TimeItem>? = null,
    val items: Map<String, CounterItem>? = null
) : ModelItem(), Parcelable

class ChartItemMapper @Inject constructor(
    private val timeItemMapper: TimeItemMapper,
    private val counterItemMapper: CounterItemMapper
) : ItemMapper<Chart, ChartItem> {
    override fun mapToPresentation(model: Chart): ChartItem = ChartItem(
        minScore = model.minScore,
        maxScore = model.maxScore,
        totalScore = model.totalScore,
        times = model.times?.map { timeItemMapper.mapToPresentation(it) },
        items = model.items?.mapValues { counterItemMapper.mapToPresentation(it.value) }
    )

    override fun mapToDomain(item: ChartItem): Chart = Chart(
        minScore = item.minScore,
        maxScore = item.maxScore,
        totalScore = item.totalScore,
        times = item.times?.map { timeItemMapper.mapToDomain(it) },
        items = item.items?.mapValues { counterItemMapper.mapToDomain(it.value) }
    )
}
