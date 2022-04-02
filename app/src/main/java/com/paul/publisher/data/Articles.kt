package com.paul.publisher.data

data class Articles(
    val author: Author,
    val category: String,
    val content: String,
    val createdTime: Long,
    val id: String,
    val title: String
)

data class Author(
    val email: String,
    val id: String,
    val name: String
)