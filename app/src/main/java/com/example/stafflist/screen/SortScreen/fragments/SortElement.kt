package com.example.stafflist.screen.SortScreen.fragments

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SortElement(
    selected: Boolean,
    selectedOption: MutableState<String>,
    text: String,
) {



    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {


        RadioButton(selected = selected, onClick = { selectedOption.value = text })

        Spacer(modifier = Modifier.width(14.dp))

        Text(text = text, fontSize = 16.sp)
    }
}