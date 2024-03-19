package com.example.stafflist.screen.ShimmerList.fragments

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition(label = "")
    val alpha = transition.animateFloat(
        initialValue = .15f,
        targetValue = .3f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    ).value

    background(color = Color(0xFFC3C3C3).copy(alpha = alpha))
}

@Composable
fun ShimmerCard() {
    Row (modifier = Modifier
        .height(84.dp)
        .fillMaxWidth()
        .clickable {

        },
        verticalAlignment = Alignment.CenterVertically){

        Box (modifier = Modifier
            .size(width = 88.dp, height =  84.dp),
            contentAlignment = Alignment.CenterStart) {
            Box(modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .shimmerEffect())
        }


        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center) {
            Box(modifier = Modifier
                .size(width = 144.dp, height = 16.dp)
                .clip(shape = RoundedCornerShape(percent = 50))
                .shimmerEffect())
            Spacer(modifier = Modifier.height(6.dp))
            Box(modifier = Modifier
                .size(width = 80.dp, height = 12.dp)
                .clip(shape = RoundedCornerShape(percent = 50))
                .shimmerEffect())
        }
    }
}

