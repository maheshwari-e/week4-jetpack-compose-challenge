package com.example.androiddevchallenge.animate

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R

@Composable
fun ClearSkyBackground(){
    Image(
        painter = painterResource(id = R.drawable.ic_clear_sky_bg),
        contentDescription = null,
        modifier = Modifier.testTag("Clear Sky Background").fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ClearSkyAnimation(){
    Box(
        modifier = Modifier.testTag("Clear Sky Animation")
            .height(250.dp)
            .fillMaxWidth()
    ) {
        SparklingStar(image = R.drawable.ic_star_bg1)
        SparklingStar(delayMillis = 150,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 20.dp, end = 20.dp),
            image = R.drawable.ic_star_bg2)
        SparklingStar(image = R.drawable.ic_star_bg3,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 50.dp),
            delayMillis = 300 )
        SparklingStar(image = R.drawable.ic_star_bg1,
            modifier = Modifier.align(Alignment.Center),
            delayMillis = 600)
        SparklingStar(image = R.drawable.ic_star_bg2,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 50.dp, bottom = 50.dp),
            delayMillis = 800)
        SparklingStar(image = R.drawable.ic_star_bg3,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 50.dp, top = 50.dp))
        SparklingStar(
            image = R.drawable.ic_star_bg1,
            modifier = Modifier.align(Alignment.BottomEnd),
            delayMillis = 150
        )
        SparklingStar(
            image = R.drawable.ic_star_bg2,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(start = 20.dp, bottom = 20.dp),
            delayMillis = 300
        )
        SparklingStar(
            image = R.drawable.ic_star_bg3,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 50.dp),
            delayMillis = 600
        )
        SparklingStar(image = R.drawable.ic_star_bg1, delayMillis =  800)
    }
}

@Composable
fun SparklingStar(modifier: Modifier = Modifier, delayMillis: Int = 0, image: Int) {
    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2000,
                delayMillis = delayMillis
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    Image(
        painter = painterResource(id = image),
        contentDescription = null,
        modifier = modifier
            .size(20.dp)
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale
            )
    )
}
