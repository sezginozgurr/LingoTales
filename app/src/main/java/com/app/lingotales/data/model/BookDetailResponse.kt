package com.app.lingotales.data.model

data class BookDetailResponse(
    val book: BookDetail,
    val pages: List<Page>
)

data class BookDetail(
    val id: Int,
    val title: String,
    val description: String,
    val coverImage: String,
    val audioUrl: String,
    val author: String,
    val category: BookCategory,
    val pageCount: Int,
    val createdAt: String,
    val updatedAt: String
)

data class BookCategory(
    val id: Int,
    val name: String
)

data class Page(
    val id: Int,
    val pageNumber: Int,
    val content: String,
    val imageUrl: String
)
