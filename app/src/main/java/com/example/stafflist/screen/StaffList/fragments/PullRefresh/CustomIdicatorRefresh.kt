package com.example.stafflist.screen.StaffList.fragments.PullRefresh

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun CustomIndicatorRefresh(
    viewModel: CustomIdnicatorViewModel,
    pullToRefreshProgress: Float
){

    val refreshIndicatorIndex = viewModel.refreshIndicatorStateIndex.collectAsState().value
    val maxHeight = 80

    val heightModifier = when(refreshIndicatorIndex){
        0 -> Modifier.height(0.dp)//Default
        1 -> Modifier.height((pullToRefreshProgress * 100).roundToInt().coerceAtMost(maxHeight).dp)//PullDown
        2 -> Modifier.height(maxHeight.dp)//ReachedTrashHold
        3 -> Modifier.wrapContentHeight()//Refreshing
        else -> {Modifier.height(0.dp)}//Default
    }

    Box (modifier = Modifier
        .fillMaxWidth()
        .animateContentSize()
        .then(heightModifier)
        .padding(15.dp),
        contentAlignment = Alignment.TopCenter){


        if(refreshIndicatorIndex == 3){
            CircularProgressIndicator(
                modifier = Modifier.size(16.dp),
                color = MaterialTheme.colorScheme.onSecondary,
                trackColor = MaterialTheme.colorScheme.secondary,
                strokeWidth = 2.dp,
            )
        }


    }


}

