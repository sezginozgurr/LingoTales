package com.app.lingotales.presentation.home.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.lingotales.R
import com.app.lingotales.core.components.LingoScaffold
import com.app.lingotales.ui.theme.LingoTalesTheme
import com.app.lingotales.ui.theme.regularBlack16Alpha50
import com.app.lingotales.util.MockDetails

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeDetailScreen(
    onBackPressed: () -> Unit,
    uiModel: HomeDetailUiModel
) {
    val title = uiModel.toolbarTitle
    val blocks = remember(title) { MockDetails.blocksFor(title) }

    LingoTalesTheme {
        LingoScaffold(
            title = title,
            showBackButton = true,
            onBackPressed = onBackPressed,
        ) { innerPadding ->
            Box(Modifier
                .fillMaxSize()
                .padding(innerPadding)) {
                // Global arka plan
                Image(
                    painter = painterResource(R.drawable.bg_home_detail),
                    contentDescription = null,
                    modifier = Modifier.matchParentSize(),
                    contentScale = ContentScale.Crop
                )

                Box(
                    Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                0f to Color.Transparent,
                                1f to Color.White.copy(alpha = 0.80f)
                            )
                        )
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 24.dp)
                ) {
                    if (blocks.isEmpty()) {
                        item {
                            Text(
                                text = "Bu hikâye için içerik bulunamadı.",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    } else {
                        // Başlık (bold)
                        item {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        items(blocks.size) { i ->
                            DetailBlockItem(blocks[i])
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun DetailBlockItem(block: DetailBlock) {
    when (block) {
        is DetailBlock.PageText -> {
            Text(
                text = block.text,
                style = regularBlack16Alpha50,
            )
        }

        is DetailBlock.PageImage -> {
            androidx.compose.material3.Card(
                shape = RoundedCornerShape(16.dp),
                elevation = androidx.compose.material3.CardDefaults.cardElevation(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Image(
                    painter = painterResource(block.imageRes),
                    contentDescription = block.contentDescription,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(4f / 3f),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}



