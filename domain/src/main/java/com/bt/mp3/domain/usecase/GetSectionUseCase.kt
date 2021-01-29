package com.bt.mp3.domain.usecase

import com.bt.mp3.domain.extension.mapToCleanException
import com.bt.mp3.domain.repository.SectionRepository
import com.bt.mp3.entity.Section
import com.bt.mp3.entity.exception.CleanException
import com.bt.mp3.entity.exception.CleanExceptionType
import javax.inject.Inject

class GetSectionUseCase @Inject constructor(
    private val repository: SectionRepository
) : UseCase<GetSectionUseCase.Param, Section>() {

    data class Param(
        val type: String
    ) : UseCase.Param()

    override suspend fun execute(param: Param?): Section = runCatching {
        param?.let {
            repository.getSection(it.type)
        } ?: throw CleanException(CleanExceptionType.PARAM_NULL)
    }.getOrElse {
        throw it.mapToCleanException()
    }

}
