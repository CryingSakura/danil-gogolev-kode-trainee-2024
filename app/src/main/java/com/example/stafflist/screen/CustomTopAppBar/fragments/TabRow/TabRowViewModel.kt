package com.example.stafflist.screen.CustomTopAppBar.fragments.TabRow

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TabRowViewModel: ViewModel(){

    val tabTitles = TabItems()

    private val _selectedIndex = MutableStateFlow(0)
    val selectedIndex = _selectedIndex.asStateFlow()

    fun updateIndex(inputIndex: Int){
        _selectedIndex.update { inputIndex }
    }
}