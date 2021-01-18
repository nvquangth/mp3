package com.bt.mp3.data.model

import com.bt.mp3.data.base.EntityMapper
import com.bt.mp3.data.base.ModelEntity
import com.bt.mp3.entity.HomePage
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class HomePageEntity(
    @SerializedName("items")
    val sections: List<SectionEntity>? = null,
    val hasMore: Boolean? = null,
    val total: Int? = null
) : ModelEntity()

class HomePageEntityMapper @Inject constructor(
    private val sectionEntityMapper: SectionEntityMapper
) : EntityMapper<HomePage, HomePageEntity> {

    override fun mapToData(model: HomePage): HomePageEntity = HomePageEntity(
        sections = model.sections?.map { sectionEntityMapper.mapToData(it) },
        hasMore = model.hasMore,
        total = model.total
    )

    override fun mapToDomain(entity: HomePageEntity): HomePage = HomePage(
        sections = entity.sections?.map { sectionEntityMapper.mapToDomain(it) },
        hasMore = entity.hasMore,
        total = entity.total
    )
}
