package com.example.stafflist.screen.StaffList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stafflist.data.ChangeDepartmentStringView
import com.example.stafflist.data.Employee
import com.example.stafflist.data.StaffListRepository
import com.example.stafflist.network.Results
import com.example.stafflist.screen.StaffList.fragments.PullRefresh.CustomIdnicatorViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StaffListViewModel(
    private val staffListRepository: StaffListRepository,
    customInputIndicatorViewModel: CustomIdnicatorViewModel
): ViewModel(){

    val customIndicatorViewModel = customInputIndicatorViewModel



    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()


    private val _staffList = MutableStateFlow<List<Employee>>(emptyList())
    val staffList =_staffList.asStateFlow()

    private val _showErrorChannel = Channel<Boolean>()
    val showErrorChannel = _showErrorChannel.receiveAsFlow()


    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true

            staffListRepository.getStaffList().collectLatest {result ->
                when(result){
                    is Results.Error ->{
                        _showErrorChannel.send(true)
                    }
                    is Results.Success -> {
                        _isRefreshing.value = false
                        customIndicatorViewModel.changeRefreshIndicatorStateIndex(0)
                        result.data?.let {staffs ->

                            _staffList.update { ChangeDepartmentStringView( staffs ) }
                        }
                    }
                }
            }

        }
    }

     fun getData(){
        viewModelScope.launch {
            staffListRepository.getStaffList().collectLatest {result ->
                when(result){
                    is Results.Error ->{
                        _showErrorChannel.send(true)
                    }
                    is Results.Success -> {
                        result.data?.let {staffs ->

                            _staffList.update { ChangeDepartmentStringView( staffs ) }
                        }
                    }
                }
            }
        }
    }



    init {
        getData()
    }
}