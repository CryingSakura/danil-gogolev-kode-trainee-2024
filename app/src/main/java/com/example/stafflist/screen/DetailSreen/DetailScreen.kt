package com.example.stafflist.screen.DetailSreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.stafflist.MainActivityViewModel
import com.example.stafflist.navigation.Graphs
import com.example.stafflist.screen.DetailSreen.fragments.DetailScreenBody
import com.example.stafflist.screen.DetailSreen.fragments.DetailScreenCap

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailSreen(viewModel: MainActivityViewModel, navController: NavController) {

    val detailSreenViewModel = viewModel.detailSreenViewModel

    val employee = detailSreenViewModel.stateEmployee.collectAsState().value



    val ctx = LocalContext.current


    Scaffold(topBar = {
        TopAppBar(title = {},

            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            ),
            navigationIcon = {
                /*img vector deprecated - change*/
                IconButton(onClick = { navController.navigate(Graphs.MAINVIEW) }) {
                    Icon(imageVector = Icons.Outlined.KeyboardArrowLeft, contentDescription = null)
                }
            })
    }) {
        Column(modifier = Modifier.fillMaxSize()){

            DetailScreenCap(employee)

            DetailScreenBody(ctx = ctx, employee = employee, viewModel)

        }
    }
}

