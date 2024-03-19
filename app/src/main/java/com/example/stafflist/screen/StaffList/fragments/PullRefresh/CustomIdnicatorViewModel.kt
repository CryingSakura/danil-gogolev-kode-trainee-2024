package com.example.stafflist.screen.StaffList.fragments.PullRefresh

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CustomIdnicatorViewModel : ViewModel(){



    private val _refreshIndicatorStateIndex = MutableStateFlow(0)
    val refreshIndicatorStateIndex = _refreshIndicatorStateIndex.asStateFlow()


    fun changeRefreshIndicatorStateIndex(index: Int){
        _refreshIndicatorStateIndex.update { index }
    }

}