package com.bt.mp3.data.base

import com.google.gson.annotations.SerializedName

data class SimpleResponse(
    @SerializedName("err")
    val errorCode: Int? = null,

    @SerializedName("msg")
    val message: String? = null,

    val timestamp: Long? = null
)
