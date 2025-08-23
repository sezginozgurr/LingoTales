package com.app.lingotales.data.model

data class MobileCategoriesResponse(
    val categories: List<Category>
)

data class Category(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String?,
    val bookCount: Int
)
