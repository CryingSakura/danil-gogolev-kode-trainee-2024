package com.example.stafflist.screen.CustomTopAppBar

import androidx.lifecycle.ViewModel
import com.example.stafflist.screen.CustomTopAppBar.fragments.Search.SearchViewModel
import com.example.stafflist.screen.CustomTopAppBar.fragments.TabRow.TabRowViewModel

class TopAppBarViewModel(tabRowsViewModel: TabRowViewModel, searchingViewModel: SearchViewModel): ViewModel(){

    val tabRowViewModel = tabRowsViewModel

    val searchViewModel = searchingViewModel


}