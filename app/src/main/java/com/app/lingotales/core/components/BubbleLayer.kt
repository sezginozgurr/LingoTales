package com.app.lingotales.core.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BubbleLayer(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Bubble(startX = 50f, startY = 1000f, size = 16.dp, duration = 8000, delay = 0)
        Bubble(startX = 200f, startY = 1100f, size = 24.dp, duration = 9000, delay = 1000)
        Bubble(startX = 350f, startY = 1200f, size = 12.dp, duration = 7000, delay = 2000)
        Bubble(startX = 150f, startY = 1050f, size = 20.dp, duration = 8500, delay = 500)
        Bubble(startX = 300f, startY = 1150f, size = 18.dp, duration = 7500, delay = 1500)
    }
}
