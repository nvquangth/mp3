package com.bt.mp3.data.base

import com.google.gson.annotations.SerializedName

abstract class BaseResponse2<T> {
    @SerializedName("err")
    val errorCode: Int? = null

    @SerializedName("msg")
    val message: String? = null

    val data: List<T>? = null

    val timestamp: Long? = null
}
