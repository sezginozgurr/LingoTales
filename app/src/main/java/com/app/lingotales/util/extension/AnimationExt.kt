package com.app.lingotales.util.extension

import androidx.compose.animation.core.EaseOutBack
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.app.lingotales.presentation.choose.CategoryUiModel

fun LazyGridScope.animatedItemsIndexed(
    items: List<CategoryUiModel>,
    itemContent: @Composable (index: Int, item: CategoryUiModel) -> Unit
) {
    itemsIndexed(items) { index, item ->
        var visible by remember { mutableStateOf(false) }
        val delay = index * 150L

        val offsetY by animateDpAsState(
            targetValue = if (visible) 0.dp else 50.dp,
            animationSpec = tween(
                durationMillis = 600,
                delayMillis = delay.toInt(),
                easing = EaseOutBack
            ), label = ""
        )
        val alpha by animateFloatAsState(
            targetValue = if (visible) 1f else 0f,
            animationSpec = tween(durationMillis = 600, delayMillis = delay.toInt()),
            label = ""
        )

        LaunchedEffect(Unit) { visible = true }

        Box(
            Modifier.graphicsLayer {
                translationY = offsetY.toPx()
                this.alpha = alpha
            }
        ) {
            itemContent(index, item)
        }
    }
}
