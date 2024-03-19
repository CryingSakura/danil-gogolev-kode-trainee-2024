package com.example.stafflist.screen.CustomTopAppBar.fragments.Search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stafflist.R
import com.example.stafflist.ui.theme.SearchBarContainerColor
import com.example.stafflist.ui.theme.SearchBarElementsColor


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
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search),
            decorationBox = { innerTextField ->
                Row(modifier = Modifier
                    .fillMaxWidth(fraction.floatValue)
                    .background(
                        color = SearchBarContainerColor,
                        shape = RoundedCornerShape(percent = 30)
                    ),

                    verticalAlignment = Alignment.CenterVertically) {



                    Box(
                        modifier = Modifier
                            .padding(12.dp)
                            .align(Alignment.CenterVertically)
                    ) {
                        Icon(painter = painterResource(id = R.drawable.my_search),
                            contentDescription = "Search Icon",
                            tint = SearchBarElementsColor)
                    }

                    Spacer(modifier = Modifier.width(8.dp))
                    if (textLocalState.value.isEmpty() && !active.value) {
                        Text(
                            text = "Введи имя, тег...",
                            color = SearchBarElementsColor,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }

                    innerTextField()


                    Spacer(modifier = Modifier.weight(1f))
                    if (textLocalState.value.isEmpty()) {
                        IconButton(
                            onClick = { isSheetOpen.value = true }
                        ) {
                            /*img vector deprecated - change*/
                            Icon(
                                painter = painterResource(id = R.drawable.list_ui_alt),
                                contentDescription = null,
                                tint = SearchBarElementsColor

                            )
                        }
                    } else {
                        IconButton(
                            onClick = {
                                textLocalState.value = ""
                            }) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = null,
                                tint = SearchBarElementsColor
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