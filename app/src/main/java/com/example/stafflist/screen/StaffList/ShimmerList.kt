package com.example.stafflist.screen.StaffList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.stafflist.screen.StaffList.fragments.ShimmerCard

@Composable
fun ShimmerList() {

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()){
            items(11){
                ShimmerCard()
            }
        }
    }
}