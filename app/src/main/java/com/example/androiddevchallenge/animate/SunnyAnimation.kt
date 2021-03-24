package com.example.androiddevchallenge.animate

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R

@Composable
fun SunnyDayAnimation() {
    val infiniteTransition = rememberInfiniteTransition()
    val scale = remember { Animatable(0f) }
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 10000, easing = LinearEasing)
        )
    )
    LaunchedEffect(true) {
        scale.animateTo(
            targetValue = 1.5f,
            animationSpec = tween(3000)
        )
    }
    Image(
        painter = painterResource(id = R.drawable.ic_sunny_bg),
        contentDescription = null,
        modifier = Modifier.testTag("Sunny Animation")
            .padding(end = 100.dp)
            .graphicsLayer(
                scaleX = scale.value,
                scaleY = scale.value,
                rotationZ = rotation
            )
    )
}
