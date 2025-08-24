package com.app.lingotales.data.model

data class CategoryBooksResponse(
    val category: CategoryBooks,
    val books: List<Book>
)

data class CategoryBooks(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String?
)

data class Book(
    val id: Int,
    val title: String,
    val description: String,
    val coverImage: String,
    val audioUrl: String,
    val author: String,
    val status: String,
    val pageCount: Int,
    val createdAt: String,
    val updatedAt: String
)

