package com.example.stafflist.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stafflist.MainActivityViewModel
import com.example.stafflist.R
import com.example.stafflist.navigation.Graphs


@Composable
fun FatalErrorPage(navController: NavController, viewModel: MainActivityViewModel){
    val staffListViewModel = viewModel.staffListViewModel

    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {



            Image(modifier = Modifier.size(56.dp),
                painter = painterResource(id = R.drawable.flying_saucer),
                contentDescription = null)

            Column(modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                
                Text(text = "Какой-то сверхразум все сломал", color = Color.Black)
                
                Text(text = "Постараемся быстро починить", color = Color.Black)
                
                TextButton(onClick = {
                    navController.navigate(Graphs.MAINVIEW)
                    staffListViewModel.getData()
                }) {
                    Text(text = "Попробовать снова")
                }
            }
        }
    }
}