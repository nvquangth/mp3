package com.bt.mp3.data.repositoryimpl

import com.bt.mp3.data.extension.mapToCleanException
import com.bt.mp3.data.model.SectionEntityMapper
import com.bt.mp3.data.network.SectionApi
import com.bt.mp3.domain.repository.SectionRepository
import com.bt.mp3.entity.Section
import com.bt.mp3.entity.exception.CleanException
import com.bt.mp3.entity.exception.CleanExceptionType
import javax.inject.Inject

class SectionRepositoryImpl @Inject constructor(
    private val api: SectionApi,
    private val sectionEntityMapper: SectionEntityMapper
) : SectionRepository {

    override suspend fun getSection(type: String): Section = runCatching {
        api.getSection(type).data?.let { sectionEntityMapper.mapToDomain(it) } ?: throw CleanException(CleanExceptionType.DATA_NULL_OR_EMPTY)
    }.getOrElse {
        throw it.mapToCleanException()
    }
}
