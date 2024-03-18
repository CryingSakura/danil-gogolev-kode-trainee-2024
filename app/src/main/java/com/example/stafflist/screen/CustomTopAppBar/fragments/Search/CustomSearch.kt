package com.example.stafflist.screen.CustomTopAppBar.fragments.Search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CustomSearch(
    isSheetOpen: MutableState<Boolean>,
    fraction: MutableFloatState,
    textLocalState: MutableState<String>,
    viewModel: SearchViewModel,
    active: MutableState<Boolean>
) {


    viewModel.updateSearchText(textLocalState.value)


    Box(modifier = Modifier
        .fillMaxWidth(fraction.floatValue)
    ) {


        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { active.value = it.hasFocus },
            value = textLocalState.value,
            onValueChange = { textLocalState.value = it },
            decorationBox = { innerTextField ->
                Row(modifier = Modifier
                    .fillMaxWidth(fraction.floatValue)
                    .background(color = Color.LightGray,
                        shape = RoundedCornerShape(percent = 30)),
                    verticalAlignment = Alignment.CenterVertically) {



                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    ) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    innerTextField()


                    Spacer(modifier = Modifier.weight(1f))
                    if (textLocalState.value.isEmpty()) {
                        IconButton(
                            onClick = { isSheetOpen.value = true }
                        ) {
                            /*img vector deprecated change*/
                            Icon(
                                imageVector = Icons.Outlined.List,
                                contentDescription = null
                            )
                        }
                    } else {
                        IconButton(
                            onClick = {
                                textLocalState.value = ""
                            }) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = null
                            )
                        }
                    }
                }
            },
            textStyle = TextStyle(
                fontSize = 15.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}