package com.example.androiddevchallenge.animate

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ParticleDropAnimation(cloud: Int, particle: Int) {
    Box(modifier = Modifier.testTag("Drizzle/Snow Animation")
        .fillMaxWidth()
        .padding(end = 100.dp)) {
        Box(modifier = Modifier
            .size(width = 129.dp, height = 84.dp).padding(4.dp)
            .align(Alignment.Center)) {
            val infiniteTransition = rememberInfiniteTransition()
            val translateY1 = infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 500f,
                animationSpec = infiniteRepeatable(tween(durationMillis = 1000, delayMillis = 600,
                    easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            )
            Image(
                painterResource(id = particle), null,
                modifier = Modifier
                    .padding(top = 28.dp)
                    .size(15.dp)
                    .graphicsLayer(translationY = translateY1.value)
            )
            val translateY2 = infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 500f,
                animationSpec = infiniteRepeatable(tween(durationMillis = 1000, delayMillis = 150,
                        easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            )
            Image(
                painterResource(id = particle), null,
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp)
                    .size(15.dp)
                    .graphicsLayer(translationY = translateY2.value)
            )
            val translateY3 = infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 500f,
                animationSpec = infiniteRepeatable(tween(durationMillis = 1000, delayMillis = 300,
                        easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            )
            Image(
                painterResource(id = particle), null,
                modifier = Modifier
                    .padding(start = 32.dp, top = 16.dp)
                    .size(15.dp)
                    .graphicsLayer(translationY = translateY3.value)
            )
            val translateY4 = infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 500f,
                animationSpec = infiniteRepeatable(tween(durationMillis = 1000, delayMillis = 0,
                        easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            )
            Image(
                painterResource(id = particle), null,
                modifier = Modifier
                    .padding(start = 48.dp, top = 16.dp)
                    .size(15.dp)
                    .graphicsLayer(translationY = translateY4.value)
            )
            val translateY5 = infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 500f,
                animationSpec = infiniteRepeatable(tween(durationMillis = 1000, delayMillis = 450,
                        easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            )
            Image(
                painterResource(id = particle), null,
                modifier = Modifier
                    .padding(start = 64.dp)
                    .size(15.dp)
                    .graphicsLayer(translationY = translateY5.value)
            )
            val translateY6 = infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 500f,
                animationSpec = infiniteRepeatable(tween(durationMillis = 1000, delayMillis = 500,
                        easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            )
            Image(
                painterResource(id = particle), null,
                modifier = Modifier
                    .padding(start = 80.dp)
                    .size(15.dp)
                    .graphicsLayer(translationY = translateY6.value)
            )
            val translateY7 = infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 500f,
                animationSpec = infiniteRepeatable(tween(durationMillis = 1000, delayMillis = 200,
                        easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            )
            Image(
                painterResource(id = particle), null,
                modifier = Modifier
                    .padding(start = 96.dp)
                    .size(15.dp)
                    .graphicsLayer(translationY = translateY7.value)
            )
            val translateY8 = infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 500f,
                animationSpec = infiniteRepeatable(tween(durationMillis = 1000, delayMillis = 100,
                        easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            )
            Image(
                painterResource(id = particle), null,
                modifier = Modifier
                    .padding(top = 12.dp, start = 112.dp)
                    .size(15.dp)
                    .graphicsLayer(translationY = translateY8.value)
            )
        }
        Image(
            painter = painterResource(id = cloud),
            contentDescription = null,
            modifier = Modifier
                .size(width = 129.dp, height = 84.dp)
                .align(Alignment.Center)
        )
    }
}
