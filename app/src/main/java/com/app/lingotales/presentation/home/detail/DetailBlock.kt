package com.app.lingotales.presentation.home.detail

import androidx.annotation.DrawableRes

sealed class DetailBlock {
    data class PageText(val text: String) : DetailBlock()
    data class PageImage(
        @DrawableRes val imageRes: Int,
        val contentDescription: String? = null
    ) : DetailBlock()
}
