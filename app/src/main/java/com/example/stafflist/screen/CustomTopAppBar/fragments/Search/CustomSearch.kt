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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stafflist.R


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
                        color = MaterialTheme.colorScheme.primaryContainer,
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
                            tint = if (active.value) MaterialTheme.colorScheme.primary
                                   else MaterialTheme.colorScheme.onSecondary
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))
                    if (textLocalState.value.isEmpty() && !active.value) {
                        Text(
                            text = stringResource(id = R.string.search_label),
                            color = MaterialTheme.colorScheme.onSecondary,
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
                                tint = if(isSheetOpen.value){
                                    MaterialTheme.colorScheme.primary
                                }else{
                                    MaterialTheme.colorScheme.onSecondary
                                }

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
                                tint = MaterialTheme.colorScheme.onSecondary
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