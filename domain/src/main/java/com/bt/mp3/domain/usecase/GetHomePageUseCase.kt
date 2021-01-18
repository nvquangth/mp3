package com.bt.mp3.domain.usecase

import com.bt.mp3.domain.extension.mapToCleanException
import com.bt.mp3.domain.repository.HomeRepository
import com.bt.mp3.entity.HomePage
import com.bt.mp3.entity.exception.CleanException
import com.bt.mp3.entity.exception.CleanExceptionType
import javax.inject.Inject

open class GetHomePageUseCase @Inject constructor(
    private val repository: HomeRepository
) : UseCase<GetHomePageUseCase.Param, HomePage>() {

    data class Param(
        val pageNumber: Int
    ) : UseCase.Param()

    override suspend fun execute(param: Param?): HomePage = runCatching {
        param?.let {
            repository.getHomePage(it.pageNumber)
        } ?: throw CleanException(CleanExceptionType.PARAM_NULL)
    }.getOrElse {
        throw it.mapToCleanException()
    }
}
