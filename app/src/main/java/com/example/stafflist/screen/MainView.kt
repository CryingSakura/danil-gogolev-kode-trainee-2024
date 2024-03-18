package com.example.stafflist.screen
import CustomModalBottomSheet
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.stafflist.MainActivityViewModel
import com.example.stafflist.navigation.Graphs
import com.example.stafflist.screen.CustomTopAppBar.CustomTopAppBar
import com.example.stafflist.screen.StaffList.ShimmerList
import com.example.stafflist.screen.StaffList.StaffList

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainView(viewModel: MainActivityViewModel, navController: NavController){

    val staffViewModel = viewModel.staffListViewModel


    val loading = staffViewModel.staffList.collectAsState().value.isEmpty()


    val errorState = viewModel.staffListViewModel.showErrorChannel.collectAsState(initial = false).value



    val isSheetOpen = remember{
        mutableStateOf(false)
    }


    if (errorState) navController.navigate(Graphs.FATALERROR)

    Column {
        CustomTopAppBar(isSheetOpen, viewModel.topAppBarViewModel)
        if (loading) {
            ShimmerList()
        } else {
            StaffList(viewModel = viewModel, navController)
        }
    }

    CustomModalBottomSheet(showSheet = isSheetOpen.value, onDismissRequest = { isSheetOpen.value = false }, viewModel = viewModel)



}