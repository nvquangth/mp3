package com.bt.mp3.data.model

import com.bt.mp3.data.base.EntityMapper
import com.bt.mp3.data.base.ModelEntity
import com.bt.mp3.entity.Section
import javax.inject.Inject

data class SectionEntity(
    val items: List<SongEntity>? = null,
    val link: String? = null,
    val sectionId: String? = null,
    val sectionType: String? = null,
    val title: String? = null,
    val viewType: String? = null,
    val chart: ChartEntity? = null,
    val chartType: String? = null
) : ModelEntity()

class SectionEntityMapper @Inject constructor(
    private val songEntityMapper: SongEntityMapper,
    private val chartEntityMapper: ChartEntityMapper
) : EntityMapper<Section, SectionEntity> {
    override fun mapToData(model: Section): SectionEntity = SectionEntity(
        items = model.items?.map { songEntityMapper.mapToData(it) },
        link = model.link,
        sectionId = model.sectionId,
        sectionType = model.sectionType,
        title = model.title,
        viewType = model.viewType,
        chart = model.chart?.let { chartEntityMapper.mapToData(it) },
        chartType = model.chartType
    )

    override fun mapToDomain(entity: SectionEntity): Section = Section(
        items = entity.items?.map { songEntityMapper.mapToDomain(it) },
        link = entity.link,
        sectionId = entity.sectionId,
        sectionType = entity.sectionType,
        title = entity.title,
        viewType = entity.viewType,
        chart = entity.chart?.let { chartEntityMapper.mapToDomain(it) },
        chartType = entity.chartType
    )
}
