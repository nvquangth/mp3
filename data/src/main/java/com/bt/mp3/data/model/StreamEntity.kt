package com.bt.mp3.data.model

import com.bt.mp3.data.base.EntityMapper
import com.bt.mp3.data.base.ModelEntity
import com.bt.mp3.entity.Stream
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class StreamEntity(
    @SerializedName("128")
    val v128: String? = null,
    @SerializedName("320")
    val v320: String? = null
) : ModelEntity()

class StreamEntityMapper @Inject constructor() : EntityMapper<Stream, StreamEntity> {
    override fun mapToData(model: Stream): StreamEntity = StreamEntity(
        v128 = model.v128,
        v320 = model.v320
    )

    override fun mapToDomain(entity: StreamEntity): Stream = Stream(
        v128 = entity.v128,
        v320 = entity.v320
    )
}
