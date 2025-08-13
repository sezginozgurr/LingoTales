package com.app.lingotales.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.app.lingotales.R
import com.app.lingotales.core.components.PrimaryButton
import com.app.tinytales.core.components.LingoTextField

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit
) {
    val focus = LocalFocusManager.current
    var name by rememberSaveable { mutableStateOf("") }
    val isValid = name.trim().length >= 3

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.login_tiger))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF59671),
                        Color(0xFFDC4044)
                    )
                )
            )
            .padding(horizontal = 32.dp, vertical = 24.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                LingoTextField(
                    value = name,
                    title = "",
                    showTitle = false,
                    onValueChange = { name = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    placeholder = { Text("İsim Girin", color = Color(0x99000000)) },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focus.clearFocus()
                            if (isValid) onLoginSuccess()
                        }
                    )
                )

                Spacer(Modifier.height(16.dp))

                PrimaryButton(
                    text = "Devam Et",
                    enabled = isValid,
                    actionClickListener = {
                        focus.clearFocus()
                        if (isValid) onLoginSuccess()
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // Alt kısım: Lottie
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        )
    }
}
