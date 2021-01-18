package com.bt.mp3.data.model

import com.bt.mp3.data.base.EntityMapper
import com.bt.mp3.data.base.ModelEntity
import com.bt.mp3.entity.PlaylistInfo
import javax.inject.Inject

data class PlaylistInfoEntity(
    val items: List<SongEntity>? = null,
    val total: Int? = null,
    val totalDuration: Long? = null
) : ModelEntity()

class PlaylistInfoEntityMapper @Inject constructor(
    private val songEntityMapper: SongEntityMapper
) : EntityMapper<PlaylistInfo, PlaylistInfoEntity> {
    override fun mapToData(model: PlaylistInfo): PlaylistInfoEntity = PlaylistInfoEntity(
        items = model.items?.map { songEntityMapper.mapToData(it) },
        total = model.total,
        totalDuration = model.totalDuration
    )

    override fun mapToDomain(entity: PlaylistInfoEntity): PlaylistInfo = PlaylistInfo(
        items = entity.items?.map { songEntityMapper.mapToDomain(it) },
        total = entity.total,
        totalDuration = entity.totalDuration
    )
}
