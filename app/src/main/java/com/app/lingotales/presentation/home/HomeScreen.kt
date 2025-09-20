package com.app.lingotales.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.app.lingotales.core.components.LingoScaffold
import com.app.lingotales.presentation.home.detail.HomeDetailUiModel
import com.app.lingotales.ui.theme.LingoTalesTheme
import com.app.lingotales.ui.theme.regularBlack16Alpha50
import com.app.lingotales.ui.theme.regularBlack24
import com.app.lingotales.util.extension.noRippleClickable
import com.app.lingotales.util.extension.orZero
import kotlin.math.absoluteValue
import kotlin.math.max

@Composable
fun HomeScreenRoute(
    navigate: () -> Unit = {},
) {

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    categoryId: Int,
    onBackPressed: () -> Unit,
    navigateDetail: (HomeDetailUiModel) -> Unit
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(categoryId) {
        viewModel.getCategoryBooks(categoryId)
    }

    val pagerState = rememberPagerState(pageCount = {
        uiState.books.size
    })

    LingoTalesTheme {
        LingoScaffold(
            title = uiState.category?.name ?: "Yükleniyor...",
            showBackButton = true,
            onBackPressed = onBackPressed,
        ) {
            Box(modifier = Modifier.fillMaxSize()) {

                when {
                    uiState.isLoading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .zIndex(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    uiState.error != null -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .zIndex(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = uiState.error ?: "Bilinmeyen hata",
                                style = regularBlack16Alpha50,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }

                    uiState.books.isEmpty() -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .zIndex(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Bu kategori için kitap bulunamadı.",
                                style = regularBlack16Alpha50,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }

                    else -> {
                        Column(modifier = Modifier.zIndex(1f)) {
                            HorizontalPager(
                                state = pagerState,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .noRippleClickable {
                                        val currentBook = uiState.books[pagerState.currentPage]
                                        navigateDetail(HomeDetailUiModel(
                                            bookId = currentBook.id.orZero(),
                                            toolbarTitle = currentBook.title.orEmpty()
                                        ))
                                    },
                                verticalAlignment = Alignment.CenterVertically,
                                contentPadding = PaddingValues(horizontal = 64.dp),
                                pageSpacing = 16.dp
                            ) { page ->
                                val pageOffset =
                                    ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
                                val scale = max(0.85f, 1f - pageOffset * 0.15f)
                                val alpha = max(0.5f, 1f - pageOffset * 0.5f)

                                val book = uiState.books[page]

                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .graphicsLayer {
                                            scaleX = scale
                                            scaleY = scale
                                            this.alpha = alpha
                                        }
                                        .wrapContentHeight()
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = book.title.orEmpty(),
                                        style = regularBlack24,
                                        modifier = Modifier.padding(bottom = 8.dp),
                                        maxLines = 2,
                                        textAlign = TextAlign.Center
                                    )

                                    Card(
                                        modifier = Modifier
                                            .aspectRatio(0.6f)
                                            .fillMaxWidth(0.6f),
                                        shape = RoundedCornerShape(16.dp),
                                        elevation = CardDefaults.cardElevation(8.dp)
                                    ) {
                                        AsyncImage(
                                            model = book.coverImage,
                                            contentDescription = book.title,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.fillMaxSize()
                                        )
                                    }

                                    Text(
                                        text = book.description.orEmpty(),
                                        style = regularBlack16Alpha50,
                                        modifier = Modifier.padding(top = 8.dp),
                                        maxLines = 3,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    val sampleBooks = listOf(
        com.app.lingotales.data.model.Book(
            id = 1,
            title = "The Little Red Riding Hood",
            description = "A classic fairy tale about a little girl who meets a wolf in the forest.",
            coverImage = "https://example.com/red-riding-hood.jpg",
            audioUrl = "https://example.com/red-riding-hood.mp3",
            author = "Traditional",
            status = "published",
            pageCount = 5,
            createdAt = "2025-08-23T21:22:28.000Z",
            updatedAt = "2025-08-23T21:22:28.000Z"
        ),
        com.app.lingotales.data.model.Book(
            id = 2,
            title = "The Three Little Pigs",
            description = "A story about three pigs who build houses and learn about hard work.",
            coverImage = "https://example.com/three-pigs.jpg",
            audioUrl = "https://example.com/three-pigs.mp3",
            author = "Traditional",
            status = "published",
            pageCount = 5,
            createdAt = "2025-08-23T21:22:29.000Z",
            updatedAt = "2025-08-23T21:22:29.000Z"
        )
    )

    val sampleCategory = com.app.lingotales.data.model.CategoryBooks(
        id = 1,
        name = "Children",
        description = "Stories for young readers",
        imageUrl = null
    )

    val mockUiState = HomeUiState(
        isLoading = false,
        category = sampleCategory,
        books = sampleBooks,
        error = null
    )

    HomeScreen(
        categoryId = 1,
        onBackPressed = {},
        navigateDetail = {}
    )
}
