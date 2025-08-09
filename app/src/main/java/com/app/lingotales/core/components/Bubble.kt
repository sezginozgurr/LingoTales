package com.app.lingotales.core.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import com.app.lingotales.R

@Composable
fun Bubble(
    startX: Float,
    startY: Float,
    size: Dp,
    duration: Int,
    delay: Int
) {
    val infiniteTransition = rememberInfiniteTransition(label = "Bubble")

    val yOffset by infiniteTransition.animateFloat(
        initialValue = startY,
        targetValue = -200f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = duration,
                delayMillis = delay,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "YOffset"
    )

    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = duration,
                delayMillis = delay,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "Alpha"
    )

    Image(
        painter = painterResource(id = R.drawable.bubble),
        contentDescription = null,
        modifier = Modifier
            .offset { IntOffset(startX.toInt(), yOffset.toInt()) }
            .size(size)
            .alpha(alpha)
    )
}


