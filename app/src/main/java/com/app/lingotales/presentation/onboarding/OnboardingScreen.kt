package com.app.lingotales.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.app.lingotales.R
import com.app.lingotales.core.components.PrimaryButton

@Composable
fun OnboardingRoute(
    viewModel: OnboardingViewModel = hiltViewModel(),
    navigateToLoginScreen: () -> Unit
) {
    OnboardingScreen{
        viewModel.onGetStartedClick()
        navigateToLoginScreen()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    navigateToLoginScreen: () -> Unit
) {
    val pages = listOf(
        OnboardingPage(
            R.raw.onboarding_animation_first,
            "Hayal Gücüyle Oku",
            "Rengarenk kitaplarla hayal gücünün sınırlarını keşfet! TinyTales ile çocukların hikaye okuma alışkanlığı gelişsin."
        ),
        OnboardingPage(
            R.raw.onboarding_animation_second,
            "Hikayeleri Dinle",
            "Dilediğin zaman, dilediğin yerde, masalları sesli dinle. Eğlenceli ve öğretici içerikler TinyTales’te!"
        ),
        OnboardingPage(
            R.raw.onboarding_animation_third,
            "Her Dilde Masal",
            "TinyTales, 5 farklı dil desteği ile dünyanın dört bir yanından hikayeleri çocuklara ulaştırıyor."
        ),
    )

    val pagerState = rememberPagerState(initialPage = 0, pageCount = { pages.size })
    val isLastPage = pagerState.currentPage == pages.lastIndex

    Column(
        Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF59671),
                        Color(0xFFDC4044)
                    )
                )
            )
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { page ->
            OnboardingPagerItem(pages[page])
        }

        Spacer(modifier = Modifier.height(32.dp))

        if (isLastPage) {
            PrimaryButton(
                text = "Get Started",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                actionClickListener = { navigateToLoginScreen() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White.copy(alpha = 0.5f),
                    disabledContainerColor = Color.White.copy(alpha = 0.3f)
                ),
            )
        } else {
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

@Composable
fun OnboardingPagerItem(page: OnboardingPage) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(page.lottieRes))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .height(400.dp)
                .padding(bottom = 32.dp)
        )

        Text(
            text = page.title,
            style = MaterialTheme.typography.displaySmall,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = page.description,
            style = MaterialTheme.typography.titleMedium,
            color = Color.DarkGray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}



@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen({ })
}