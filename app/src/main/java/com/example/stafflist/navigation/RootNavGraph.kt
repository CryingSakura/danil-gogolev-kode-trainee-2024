package com.example.stafflist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.stafflist.MainActivityViewModel
import com.example.stafflist.screen.DetailScreen.DetailScreen
import com.example.stafflist.screen.FatalErrorPage
import com.example.stafflist.screen.MainView

@Composable
fun RootNavigationGraph(navController: NavHostController, viewModel: MainActivityViewModel){
    NavHost(
        navController = navController,
        route = Graphs.ROOT,
        startDestination = Graphs.MAINVIEW){
        composable(Graphs.MAINVIEW){
            MainView(viewModel = viewModel, navController)
        }
        composable(route = Graphs.DETAILSCREEN){
            DetailScreen(viewModel = viewModel, navController)
        }
        composable(route = Graphs.FATALERROR){
            FatalErrorPage(navController = navController, viewModel = viewModel)
        }

    }
}