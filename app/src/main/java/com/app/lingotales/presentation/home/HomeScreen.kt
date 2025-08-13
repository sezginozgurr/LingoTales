package com.app.lingotales.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.app.lingotales.core.components.LingoScaffold
import com.app.lingotales.navigation.Destination
import com.app.lingotales.presentation.choose.CategoryType
import com.app.lingotales.presentation.home.detail.HomeDetailUiModel
import com.app.lingotales.ui.theme.LingoTalesTheme
import com.app.lingotales.util.MockBooks
import com.app.lingotales.util.extension.noRippleClickable
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
    type: CategoryType,
    onBackPressed: () -> Unit,
    navigateDetail: (HomeDetailUiModel) -> Unit
) {
    val books = remember(type) { MockBooks.forType(type) }
    val pagerState = rememberPagerState(pageCount = { books.size })

    LingoTalesTheme {
        LingoScaffold(
            title = MockBooks.titleFor(type),
            showBackButton = true,
            onBackPressed = onBackPressed,
        ) {
            Box(modifier = Modifier.fillMaxSize()) {

                if (books.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .zIndex(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Bu kategori için içerik bulunamadı.")
                    }
                } else {
                    Column(modifier = Modifier.zIndex(1f)) {
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier
                                .fillMaxSize()
                                .noRippleClickable {
                                    navigateDetail(HomeDetailUiModel(books[pagerState.currentPage].title))

                                },
                            verticalAlignment = Alignment.CenterVertically,
                            contentPadding = PaddingValues(horizontal = 64.dp),
                            pageSpacing = 16.dp
                        ) { page ->
                            val pageOffset =
                                ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
                            val scale = max(0.85f, 1f - pageOffset * 0.15f)
                            val alpha = max(0.5f, 1f - pageOffset * 0.5f)

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
                                    text = books[page].title,
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier.padding(bottom = 8.dp),
                                    maxLines = 1
                                )
                                Card(
                                    modifier = Modifier
                                        .aspectRatio(0.6f)
                                        .fillMaxWidth(0.6f),
                                    shape = RoundedCornerShape(16.dp),
                                    elevation = CardDefaults.cardElevation(8.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = books[page].imageRes),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }
                                Text(
                                    text = books[page].subtitle,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(top = 8.dp),
                                    maxLines = 1
                                )
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
    HomeScreen(type = CategoryType.HIKAYELER, {}) {}
}
