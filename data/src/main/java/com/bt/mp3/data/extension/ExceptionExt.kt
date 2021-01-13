package com.bt.mp3.data.extension

import com.bt.mp3.data.model.ErrorResponseEntity
import com.bt.mp3.entity.exception.CleanException
import com.bt.mp3.entity.exception.CleanExceptionType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.HttpException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.mapToCleanException(): CleanException =
    when (this) {
        is CleanException -> this

        is UnknownHostException -> CleanException(CleanExceptionType.NETWORK_NO_CONNECTION, message)

        is SocketTimeoutException -> CleanException(CleanExceptionType.NETWORK_TIMEOUT, "message")

        is HttpException -> {

            val gson = Gson()
            val typeToken = object : TypeToken<ErrorResponseEntity>() {}.type
            val errorResponse: ErrorResponseEntity? = gson.fromJson(this.response()?.errorBody()?.charStream(), typeToken)
            val errorType = errorResponse?.type
            val errorMsg = errorResponse?.message

            when (code()) {
                // 400
                HttpURLConnection.HTTP_BAD_REQUEST -> CleanException(CleanExceptionType.NETWORK_BAD_REQUEST)

                // 401
                HttpURLConnection.HTTP_UNAUTHORIZED -> CleanException(CleanExceptionType.UNAUTHORIZED, errorMsg)

                // 404
                HttpURLConnection.HTTP_NOT_FOUND -> CleanException(CleanExceptionType.NETWORK_BAD_REQUEST)

                // 403
                HttpURLConnection.HTTP_FORBIDDEN -> CleanException(CleanExceptionType.NETWORK_BAD_REQUEST)

                // 500
                HttpURLConnection.HTTP_INTERNAL_ERROR -> CleanException(CleanExceptionType.SERVER_MAINTENANCE)

                // 503
                HttpURLConnection.HTTP_UNAVAILABLE -> {
                    when (errorType) {
                        ErrorResponseEntity.TYPE_APP_FORCE_UPDATE -> CleanException(CleanExceptionType.APP_FORCE_UPDATE, errorMsg)

                        ErrorResponseEntity.TYPE_SERVER_MAINTENANCE -> CleanException(CleanExceptionType.SERVER_MAINTENANCE, errorMsg)

                        else -> CleanException(CleanExceptionType.UNKNOWN, message)
                    }
                }

                else -> CleanException(CleanExceptionType.UNKNOWN, message)
            }
        }
        else -> CleanException(CleanExceptionType.UNKNOWN, message)
    }
