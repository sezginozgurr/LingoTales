package com.app.lingotales.pager

import androidx.annotation.DrawableRes

data class Headline(
    val title: String,
    val description: String,
    val category: String,
    val contentDescription: String,
    @DrawableRes val image: Int = 0,
    val imageUrl: String = ""
)