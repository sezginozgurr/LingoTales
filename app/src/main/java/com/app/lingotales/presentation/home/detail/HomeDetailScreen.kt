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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.lingotales.core.components.LingoScaffold
import com.app.lingotales.pager.FakeAppBar
import com.app.lingotales.pager.HeadlineArticle
import com.app.lingotales.pager.flip.FlipPager
import com.app.lingotales.pager.flip.FlipPagerOrientation
import com.app.lingotales.ui.theme.LingoTalesTheme
import androidx.compose.ui.tooling.preview.Preview
import com.app.lingotales.pager.Headline

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeDetailScreen(
    onBackPressed: () -> Unit,
    uiModel: HomeDetailUiModel
) {
    val viewModel: HomeDetailViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiModel.bookId) {
        viewModel.getBookDetail(uiModel.bookId)
    }

    LingoTalesTheme {
        LingoScaffold(
            title = uiState.book?.title ?: "Yükleniyor...",
            showBackButton = true,
            onBackPressed = onBackPressed,
        ) { innerPadding ->
            Column(
                Modifier.navigationBarsPadding()
            ) {
                var orientation: FlipPagerOrientation by remember {
                    mutableStateOf(FlipPagerOrientation.Vertical)
                }
                
                val pagerState = rememberPagerState { uiState.pages.size }
                
                FakeAppBar(
                    orientation = orientation,
                    darkMode = false,
                    setOrientation = { if (!pagerState.isScrollInProgress) orientation = it },
                    setDarkMode = { false }
                )

                FlipPager(
                    state = pagerState,
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
                            headline = uiState.pages.getOrNull(page)?.let { pageData ->
                                Headline(
                                    title = "Sayfa ${pageData.pageNumber}",
                                    description = pageData.content,
                                    category = uiState.book?.category?.name ?: "",
                                    contentDescription = pageData.content,
                                    image = 0,
                                    imageUrl = pageData.imageUrl
                                )
                            } ?: Headline(
                                title = "Yükleniyor...",
                                description = "",
                                category = "",
                                contentDescription = "",
                                image = 0,
                                imageUrl = ""
                            )
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeDetailScreenPreview() {
    HomeDetailScreen(
        onBackPressed = {},
        uiModel = HomeDetailUiModel(bookId = 2, toolbarTitle = "The Three Little Pigs")
    )
}



