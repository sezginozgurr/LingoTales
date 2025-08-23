package com.app.lingotales.presentation.choose

data class CategoryUiModel(
    val id: String,
    val title: String,
    val description: String = "",
    val imageUrl: String? = null,
    val imageRes: Int? = null,
    val type: CategoryType
)
