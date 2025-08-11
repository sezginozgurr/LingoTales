package com.app.lingotales.presentation.home

import androidx.annotation.DrawableRes

data class PagerItem(
    val title: String,
    val subtitle: String,
    @DrawableRes val imageRes: Int
)
