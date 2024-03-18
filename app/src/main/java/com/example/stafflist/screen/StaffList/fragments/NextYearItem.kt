package com.example.stafflist.screen.StaffList.fragments

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun NextYearItem(nextYear: Int){
    Log.e("GogaYep", "zdes")

    Row (modifier = Modifier
        .height(40.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){
        Spacer(modifier = Modifier.weight(1f))
        Divider(modifier = Modifier.width(72.dp), thickness = 1.dp, color = Color.Black)
        Text(modifier = Modifier
            .height(20.dp)
            .width(160.dp),
            text = "$nextYear",
            color = Color.Black,
            textAlign = TextAlign.Center)
        Divider(modifier = Modifier.width(72.dp), thickness = 1.dp, color = Color.Black)
        Spacer(modifier = Modifier.weight(1f))
    }
}