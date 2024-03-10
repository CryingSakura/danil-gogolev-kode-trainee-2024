package com.example.stafflist.screen.StaffList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.stafflist.screen.StaffList.fragments.EmployeeCard

@Composable
fun StaffList(viewModel: StaffListViewModel){

    val staffList = viewModel.staffList.collectAsState().value



    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()){
            items(staffList){employee ->
                EmployeeCard(employee)
            }
        }
    }
}