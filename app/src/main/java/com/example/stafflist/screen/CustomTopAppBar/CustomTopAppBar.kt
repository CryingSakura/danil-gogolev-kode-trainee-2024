package com.example.stafflist.screen.CustomTopAppBar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.stafflist.screen.CustomTopAppBar.fragments.Search.CustomSearch
import com.example.stafflist.screen.CustomTopAppBar.fragments.TabRow.CustomTabRows


@Composable
fun CustomTopAppBar(isSheetOpen: MutableState<Boolean>, topAppBarViewModel: TopAppBarViewModel) {



    val active = remember{
        mutableStateOf(false)
    }

    val tabRowViewModel = topAppBarViewModel.tabRowViewModel

    val searchViewModel = topAppBarViewModel.searchViewModel

    val focusManager = LocalFocusManager.current


    val textLocalState = remember {
        mutableStateOf("")
    }

    val fraction = remember{
        mutableFloatStateOf(1f)
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.2f)) {
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween) {

                Spacer(modifier = Modifier.weight(1f))

                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .weight(1f),
                ){

                    CustomSearch(isSheetOpen, fraction, textLocalState, searchViewModel, active)

                    if (active.value/* || (textLocalState.value.isEmpty() && active.value)*/){
                        fraction.floatValue = 0.79f
                        TextButton(modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                fraction.floatValue = 1f
                                textLocalState.value = ""
                                focusManager.clearFocus()
                            }) {
                            Text(text = "Cancel", color = Color.Red)
                        }
                    }
                }
                CustomTabRows(tabRowViewModel) }
    }
}