package com.app.lingotales.presentation.home.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.app.lingotales.core.components.LingoScaffold
import com.app.lingotales.pager.FakeAppBar
import com.app.lingotales.pager.HeadlineArticle
import com.app.lingotales.pager.flip.FlipPager
import com.app.lingotales.pager.flip.FlipPagerOrientation
import com.app.lingotales.pager.headlines
import com.app.lingotales.ui.theme.LingoTalesTheme
import com.app.lingotales.util.MockDetails

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeDetailScreen(
    onBackPressed: () -> Unit,
    uiModel: HomeDetailUiModel
) {

    LingoTalesTheme {
        LingoScaffold(
            title = "(Kitap ismi)",
            showBackButton = true,
            onBackPressed = onBackPressed,
        ) { innerPadding ->
            Column(
                Modifier.navigationBarsPadding()
            ) {
                var orientation: FlipPagerOrientation by remember {
                    mutableStateOf(FlipPagerOrientation.Vertical)
                }
                val state = rememberPagerState { headlines.size }
                FakeAppBar(
                    orientation = orientation,
                    darkMode = false,
                    setOrientation = { if (!state.isScrollInProgress) orientation = it },
                    setDarkMode = { false }
                )

                FlipPager(
                    state = state,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    orientation = orientation,
                ) { page ->
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .clip(RoundedCornerShape(16.dp)),
                    ) {
                        HeadlineArticle(
                            modifier = Modifier.align(Alignment.Center),
                            headline = headlines[page],
                        )
                    }
                }
            }
        }
    }
}



