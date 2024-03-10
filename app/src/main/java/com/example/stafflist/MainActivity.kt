package com.example.stafflist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stafflist.network.RetrofitInstance
import com.example.stafflist.network.StaffListRepImplementation
import com.example.stafflist.screen.MainView
import com.example.stafflist.screen.StaffList.StaffListViewModel
import com.example.stafflist.ui.theme.StaffListTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<StaffListViewModel>(factoryProducer = {
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return StaffListViewModel(StaffListRepImplementation(RetrofitInstance.requestService)) as T
            }
        }
    })


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StaffListTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainView(viewModel = viewModel)
                }
            }
        }
    }
}
