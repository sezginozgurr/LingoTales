package com.app.lingotales.presentation.home.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.app.lingotales.R
import com.app.lingotales.core.components.LingoScaffold
import com.app.lingotales.presentation.choose.CategoryType
import com.app.lingotales.ui.theme.LingoTalesTheme
import com.app.lingotales.ui.theme.regularBlack14Alpha50
import com.app.lingotales.ui.theme.regularBlack16Alpha50
import kotlin.math.absoluteValue
import kotlin.math.max

@Composable
fun HomeScreenRoute(
    navigate: () -> Unit = {},
) {

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeDetailScreen(
    onBackPressed: () -> Unit
) {
    LingoTalesTheme {
        LingoScaffold(
            title = "uiModel.toolbarTitle",
            showBackButton = true,
            onBackPressed = onBackPressed,
        ) {
            Box(Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(R.drawable.bg_home_detail),
                    contentDescription = null,
                    modifier = Modifier.matchParentSize(),
                    contentScale = ContentScale.Crop
                )

                // Scrollable içerik
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    Text(
                        text = "uiModel.toolbarTitle",
                        style = regularBlack14Alpha50,
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "uzun text" ?: mockLongText,
                        style = regularBlack16Alpha50,
                    )
                    Spacer(Modifier.height(24.dp))
                }
            }
        }
    }
}

private val mockLongText = buildString {
    repeat(8) {
        appendLine("Rüzgâr tatlı tatlı eserken küçük okur sayfaları çevirdi; her cümle yeni bir kapı açtı.")
        appendLine("Kahramanlar bazen fısıldadı, bazen güldü; bazen de sadece dinledi.")
        appendLine()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeDetailScreenPreview() {

    HomeDetailScreen(
        onBackPressed = {}
    )
}



