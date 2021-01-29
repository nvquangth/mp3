package com.bt.mp3.entity

data class Chart(
    val minScore: Float? = null,
    val maxScore: Float? = null,
    val totalScore: Long? = null,
    val times: List<Time>? = null,
    val items: Map<String, Counter>? = null
) : Model()