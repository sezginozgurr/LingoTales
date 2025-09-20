package com.app.lingotales.data.model

data class CategoryBooksResponse(
    val category: CategoryBooks? = null,
    val books: List<Book>? = null
)

data class CategoryBooks(
    val id: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val imageUrl: String? = null
)

data class Book(
    val id: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val coverImage: String? = null,
    val audioUrl: String? = null,
    val author: String? = null,
    val status: String? = null,
    val pageCount: Int? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null
)

