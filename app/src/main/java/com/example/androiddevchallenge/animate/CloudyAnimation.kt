package com.example.androiddevchallenge.animate

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R

@Composable
fun CloudyAnimation(){
    val infiniteTransition = rememberInfiniteTransition()
    val translateX = infiniteTransition.animateFloat(
        initialValue = -100f,
        targetValue = 100f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val scale = remember { Animatable(0f) }
    LaunchedEffect(true) {
        scale.animateTo(1.5f, animationSpec = tween(3000))
    }

    Image(
        painter = painterResource(id = R.drawable.ic_cloud_overlap),
        contentDescription = null,
        modifier = Modifier.testTag("Cloudy Animation")
            .padding(end = 100.dp)
            .size(100.dp)
            .graphicsLayer(
                scaleX = scale.value,
                scaleY = scale.value,
                translationX = translateX.value
            )
    )
}
