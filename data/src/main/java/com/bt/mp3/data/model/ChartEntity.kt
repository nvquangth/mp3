package com.bt.mp3.data.model

import com.bt.mp3.data.base.EntityMapper
import com.bt.mp3.data.base.ModelEntity
import com.bt.mp3.entity.Chart
import javax.inject.Inject

data class ChartEntity(
    val minScore: Float? = null,
    val maxScore: Float? = null,
    val totalScore: Long? = null,
    val times: List<TimeEntity>? = null,
    val items: Map<String, List<CounterEntity>>? = null
) : ModelEntity()

class ChartEntityMapper @Inject constructor(
    private val timeEntityMapper: TimeEntityMapper,
    private val counterEntityMapper: CounterEntityMapper
) : EntityMapper<Chart, ChartEntity> {
    override fun mapToData(model: Chart): ChartEntity = ChartEntity(
        minScore = model.minScore,
        maxScore = model.maxScore,
        totalScore = model.totalScore,
        times = model.times?.map { timeEntityMapper.mapToData(it) },
        items = model.items?.mapValues { it.value.map { counterEntityMapper.mapToData(it) } }
    )

    override fun mapToDomain(entity: ChartEntity): Chart = Chart(
        minScore = entity.minScore,
        maxScore = entity.maxScore,
        totalScore = entity.totalScore,
        times = entity.times?.map { timeEntityMapper.mapToDomain(it) },
        items = entity.items?.mapValues { it.value.map { counterEntityMapper.mapToDomain(it) } }
    )
}
