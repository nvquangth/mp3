package com.bt.mp3.data.base

import com.google.gson.annotations.SerializedName

abstract class BaseResponse<T> {
    @SerializedName("err")
    val errorCode: Int? = null

    @SerializedName("msg")
    val message: String? = null

    val data: T? = null

    val timestamp: Long? = null
}
