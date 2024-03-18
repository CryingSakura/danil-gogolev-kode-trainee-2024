package com.example.stafflist.screen.SortScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class SortViewModel(): ViewModel() {

    private val _indexOfSelectedItem = MutableStateFlow(0)
    val indexOfSelectedItem = _indexOfSelectedItem.asStateFlow()

    fun changeIndex(index: Int){
        _indexOfSelectedItem.update { index }
    }


}