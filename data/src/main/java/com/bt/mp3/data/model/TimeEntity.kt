package com.bt.mp3.data.model

import com.bt.mp3.data.base.EntityMapper
import com.bt.mp3.data.base.ModelEntity
import com.bt.mp3.entity.Time
import javax.inject.Inject

data class TimeEntity(
    val hour: String? = null
) : ModelEntity()

class TimeEntityMapper @Inject constructor() : EntityMapper<Time, TimeEntity> {
    override fun mapToData(model: Time): TimeEntity = TimeEntity(
        hour = model.hour
    )

    override fun mapToDomain(entity: TimeEntity): Time = Time(
        hour = entity.hour
    )
}
