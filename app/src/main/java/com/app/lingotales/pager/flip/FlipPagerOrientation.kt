package com.app.lingotales.pager.flip

sealed class FlipPagerOrientation {
    data object Vertical : FlipPagerOrientation()
    data object Horizontal : FlipPagerOrientation()
}