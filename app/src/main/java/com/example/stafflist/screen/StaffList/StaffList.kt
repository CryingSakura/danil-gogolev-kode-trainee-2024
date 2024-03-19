package com.example.stafflist.screen.StaffList

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stafflist.MainActivityViewModel
import com.example.stafflist.data.DateChanger
import com.example.stafflist.screen.StaffList.fragments.EmployeeCard
import com.example.stafflist.screen.StaffList.fragments.NextYearItem
import com.example.stafflist.screen.StaffList.fragments.PullRefresh.CustomIndicatorRefresh
import kotlinx.coroutines.delay
import java.util.Calendar

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class,
    ExperimentalAnimationApi::class
)
@Composable
fun StaffList(viewModel: MainActivityViewModel, navController: NavController){

    val tabRowViewModel = viewModel.topAppBarViewModel.tabRowViewModel
    val searchViewModel = viewModel.topAppBarViewModel.searchViewModel
    val staffListViewModel = viewModel.staffListViewModel
    val sortViewModel = viewModel.sortViewModel
    val customIndicatorViewModel = viewModel.staffListViewModel.customIndicatorViewModel

    val searchText = searchViewModel.searchText.collectAsState().value


    val selectedIndex = tabRowViewModel.selectedIndex.collectAsState().value

    val tabListItems = tabRowViewModel.tabTitles.tabListItems

    val staffList = viewModel.staffListViewModel.staffList.collectAsState().value

    val sortId = sortViewModel.indexOfSelectedItem.collectAsState().value

    val calendar = Calendar.getInstance()

    val currentYear = calendar.get(Calendar.YEAR)
    val nextYear = currentYear + 1
    val currentMonth = calendar.get(Calendar.MONTH) + 1

    val currentDay = calendar.get(Calendar.DAY_OF_MONTH)










    val pagerState = rememberPagerState{
        tabListItems.size
    }

    val loading = remember{
        mutableStateOf(true)
    }


    LaunchedEffect(key1 = true, block = {
        delay(10_000)
        loading.value = false
    })


    val sortedList = when (sortId) {
        0 -> staffList.sortedBy { it.firstName }
        1 -> {
            staffList.sortedBy { DateChanger(it.birthday).day }
            staffList.sortedBy { DateChanger(it.birthday).month }
        }
        else -> {staffList.sortedBy { it.id }}
    }

    sortedList.forEach {
        Log.d("GogaMyMonth", "${DateChanger(it.birthday).month}")
        if (DateChanger(it.birthday).month < currentMonth) {
            it.dateInNextYear = true
            Log.e("GogaWork", "$it")
        }else if (DateChanger(it.birthday).month == currentMonth && DateChanger(it.birthday).day < currentDay){
            it.dateInNextYear = true
        }
    }

    Log.e("GogaCurrentMonth", "$currentMonth")
    Log.e("GogaCurrentDay", "$currentDay")
    Log.e("GogaCurrentYear", "$currentYear")

    var haveBirthdayNextYear = false

    for (i in 0 until sortedList.size){
        if (sortedList[i].dateInNextYear){
            haveBirthdayNextYear = true
            break
        }
    }
    Log.e("GogaHaveBirthday","$haveBirthdayNextYear")

    /*var show: Boolean = false*/
/*
    staffListViewModel.sortByIndex(sortId, viewModel.staffListViewModel.staffList)*/


    Log.e("GogaSortTestStaff", "${sortViewModel.indexOfSelectedItem.collectAsState().value}")
    Log.e("GogaSortTestStaff", "${sortViewModel.indexOfSelectedItem}")




    val isRefreshing = staffListViewModel.isRefreshing.collectAsState().value
    val refreshing = remember{
        mutableStateOf(isRefreshing)
    }
    val pullRefreshState = rememberPullRefreshState(refreshing = refreshing.value,
        onRefresh = {
            customIndicatorViewModel.changeRefreshIndicatorStateIndex(3)
            staffListViewModel.refresh()



            },
        refreshThreshold = 90.dp)



    LaunchedEffect(key1 = pullRefreshState.progress){
        when {
            pullRefreshState.progress >= 1 -> {
                customIndicatorViewModel.changeRefreshIndicatorStateIndex(2)
            }

            pullRefreshState.progress > 0 -> {
                customIndicatorViewModel.changeRefreshIndicatorStateIndex(1)
            }
        }
    }










    HorizontalPager(state = pagerState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
        )
        {
            CustomIndicatorRefresh(
                viewModel = customIndicatorViewModel,
                pullToRefreshProgress = pullRefreshState.progress
            )

            Box(modifier = Modifier.weight(1f)) {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    if (!refreshing.value) {
                        items(items = sortedList.filter {

                            if (tabListItems[selectedIndex] == "All") {
                                tabListItems[selectedIndex] == "All"
                            } else {
                                it.department == tabListItems[selectedIndex]
                            }
                        }.filter {
                            it.firstName.lowercase().startsWith(searchText.lowercase()) ||
                                    it.lastName.lowercase().startsWith(searchText.lowercase())
                        }) { employee ->
                            if (sortId == 0) {
                                EmployeeCard(employee, viewModel, navController)
                            }

                            if ((sortId == 1) && (!employee.dateInNextYear)) {
                                EmployeeCard(employee, viewModel, navController)
                            }
                        }
                        item {
                            if (sortId == 1 && haveBirthdayNextYear) {
                                NextYearItem(nextYear = nextYear)
                            }
                        }
                        if (sortId == 1) {
                            items(items = sortedList.filter {
                                if (tabListItems[selectedIndex] == "All") {
                                    tabListItems[selectedIndex] == "All"
                                } else {
                                    it.department == tabListItems[selectedIndex]
                                }
                            }.filter {
                                it.firstName.lowercase().startsWith(searchText.lowercase()) ||
                                        it.lastName.lowercase().startsWith(searchText.lowercase())
                            }) { employee ->
                                if (employee.dateInNextYear) {
                                    EmployeeCard(employee, viewModel, navController)
                                }

                            }
                        }

                    }
                }

                Log.e("GogaSortID", "$sortId")



                if (sortId == 1) {

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        if (refreshing.value) {
                            items(items = sortedList.filter {
                                if (tabListItems[selectedIndex] == "All") {
                                    tabListItems[selectedIndex] == "All"
                                } else {
                                    it.department == tabListItems[selectedIndex]
                                }
                            }.filter {
                                it.firstName.lowercase().startsWith(searchText.lowercase()) ||
                                        it.lastName.lowercase().startsWith(searchText.lowercase())
                            }) { employee ->
                                if (employee.dateInNextYear) {
                                    EmployeeCard(employee, viewModel, navController)
                                    NextYearItem(nextYear = nextYear)
                                }

                            }
                        }
                    }
                }
            }







        }

    }
}


