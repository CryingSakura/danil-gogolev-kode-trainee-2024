package com.example.stafflist

import androidx.lifecycle.ViewModel
import com.example.stafflist.screen.CustomTopAppBar.TopAppBarViewModel
import com.example.stafflist.screen.DetailSreen.DetailSreenViewModel
import com.example.stafflist.screen.SortScreen.SortViewModel
import com.example.stafflist.screen.StaffList.StaffListViewModel

class MainActivityViewModel(
    staffViewModel: StaffListViewModel,
    topAppBarInputViewModel: TopAppBarViewModel,
    detailInputViewModel: DetailSreenViewModel,
    sortInputViewModel: SortViewModel): ViewModel(){

    val staffListViewModel = staffViewModel
    val topAppBarViewModel = topAppBarInputViewModel
    val detailSreenViewModel = detailInputViewModel
    val sortViewModel = sortInputViewModel


}
