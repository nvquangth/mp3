package com.bt.mp3.entity

data class Counter(
    val time: Long? = null,
    val hour: String? = null,
    val counter: Long? = null
) : Model()