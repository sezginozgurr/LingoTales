package com.app.lingotales.presentation.choose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.lingotales.R

@Composable
fun ChooseScreen(
    onNavigateToHome: () -> Unit,
    viewModel: ChooseViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Kategori Seçin",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            // First row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CategoryCard(
                    title = "Masallar",
                    modifier = Modifier.weight(1f),
                    backgroundImageRes = R.drawable.masallar,
                    onClick = {
                        viewModel.onEvent(ChooseUiEvent.SelectCategory("Masallar"))
                        onNavigateToHome()
                    }
                )

                CategoryCard(
                    title = "Hikayeler",
                    modifier = Modifier.weight(1f),
                    backgroundImageRes = R.drawable.egitici,
                    onClick = {
                        viewModel.onEvent(ChooseUiEvent.SelectCategory("Hikayeler"))
                        onNavigateToHome()
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Second row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CategoryCard(
                    title = "Eğitici Hikayeler",
                    modifier = Modifier.weight(1f),
                    backgroundImageRes = R.drawable.egitici,
                    onClick = {
                        viewModel.onEvent(ChooseUiEvent.SelectCategory("Eğitici Hikayeler"))
                        onNavigateToHome()
                    }
                )

                CategoryCard(
                    title = "Hayvan Hikayeleri",
                    modifier = Modifier.weight(1f),
                    backgroundImageRes = R.drawable.hayvan_hikayesi,
                    onClick = {
                        viewModel.onEvent(ChooseUiEvent.SelectCategory("Hayvan Hikayeleri"))
                        onNavigateToHome()
                    }
                )
            }
        }
    }
}

@Composable
private fun CategoryCard(
    title: String,
    modifier: Modifier = Modifier,
    backgroundImageRes: Int? = null,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .size(150.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2D2D2D)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Background image if provided
            backgroundImageRes?.let { imageRes ->
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.FillBounds
                )
            }

            // Semi-transparent overlay for better text readability
            if (backgroundImageRes != null) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.4f))
                )
            }

            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
} 