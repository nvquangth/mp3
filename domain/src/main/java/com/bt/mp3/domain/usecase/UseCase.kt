package com.bt.mp3.domain.usecase

abstract class UseCase<in P : UseCase.Param, out T> where T : Any {

    abstract suspend fun execute(param: P? = null): T

    open class Param
}
