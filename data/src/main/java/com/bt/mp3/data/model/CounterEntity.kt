package com.bt.mp3.data.model

import com.bt.mp3.data.base.EntityMapper
import com.bt.mp3.data.base.ModelEntity
import com.bt.mp3.entity.Counter
import javax.inject.Inject

data class CounterEntity(
    val time: Long? = null,
    val hour: String? = null,
    val counter: Long? = null
) : ModelEntity()

class CounterEntityMapper @Inject constructor() : EntityMapper<Counter, CounterEntity> {
    override fun mapToData(model: Counter): CounterEntity = CounterEntity(
        time = model.time,
        hour = model.hour,
        counter = model.counter
    )

    override fun mapToDomain(entity: CounterEntity): Counter = Counter(
        time = entity.time,
        hour = entity.hour,
        counter = entity.counter
    )
}
