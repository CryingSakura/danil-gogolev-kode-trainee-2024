package com.example.stafflist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.stafflist.navigation.RootNavigationGraph
import com.example.stafflist.network.RetrofitInstance
import com.example.stafflist.network.StaffListRepImplementation
import com.example.stafflist.screen.CustomTopAppBar.TopAppBarViewModel
import com.example.stafflist.screen.CustomTopAppBar.fragments.Search.SearchViewModel
import com.example.stafflist.screen.CustomTopAppBar.fragments.TabRow.TabRowViewModel
import com.example.stafflist.screen.DetailSreen.DetailSreenViewModel
import com.example.stafflist.screen.SortScreen.SortViewModel
import com.example.stafflist.screen.StaffList.StaffListViewModel
import com.example.stafflist.screen.StaffList.fragments.PullRefresh.CustomIdnicatorViewModel
import com.example.stafflist.ui.theme.StaffListTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainActivityViewModel>(factoryProducer = {
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainActivityViewModel(
                    StaffListViewModel(StaffListRepImplementation(RetrofitInstance.requestService),
                        CustomIdnicatorViewModel()),
                    TopAppBarViewModel(
                        TabRowViewModel(),
                        SearchViewModel()),
                    DetailSreenViewModel(),
                    SortViewModel()
                ) as T
            }
        }
    })




    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, true)

        enableEdgeToEdge()
        setContent {
            StaffListTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootNavigationGraph(navController = rememberNavController(), viewModel = viewModel)
                }
            }
        }
    }
}
