package com.app.lingotales.presentation.home.detail

import kotlinx.serialization.Serializable

@Serializable
data class HomeDetailUiModel(
    val bookId: Int,
    val toolbarTitle: String = ""
)
