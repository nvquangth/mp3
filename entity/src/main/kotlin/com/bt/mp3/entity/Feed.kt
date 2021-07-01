package com.bt.mp3.entity

data class Feed(
    val id: String,
    val content: ContentFeed? = null,
    val type: Int? = null,
    val title: String? = null,
    val createdTime: Long? = null,
    val publishTime: Long? = null,
    val shortDescription: String? = null,
    val description: String? = null,
    val sourceType: Int? = null,
    val publisher: Artist? = null,
    val status: String? = null,
    val like: Long? = null,
    val comment: Long? = null,
    val liked: Boolean? = null
) : Model()
