package com.example.stafflist.screen.StaffList

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import com.example.stafflist.screen.StaffList.fragments.NotFound
import com.example.stafflist.screen.StaffList.fragments.PullRefresh.CustomIndicatorRefresh
import kotlinx.coroutines.delay
import java.util.Calendar

@OptIn(
    ExperimentalFoundationApi::class, ExperimentalMaterialApi::class
)
@Composable
fun StaffList(viewModel: MainActivityViewModel, navController: NavController) {

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


    val pagerState = rememberPagerState {
        tabListItems.size
    }

    val loading = remember {
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

        else -> {
            staffList.sortedBy { it.id }
        }
    }

    sortedList.forEach {
        if (DateChanger(it.birthday).month < currentMonth) {
            it.dateInNextYear = true
        } else if (DateChanger(it.birthday).month == currentMonth && DateChanger(it.birthday).day < currentDay) {
            it.dateInNextYear = true
        }
    }

    var haveBirthdayNextYear = false

    for (element in sortedList) {
        if (element.dateInNextYear) {
            haveBirthdayNextYear = true
            break
        }
    }
    Log.e("GogaHaveBirthday", "$haveBirthdayNextYear")


    val isRefreshing = staffListViewModel.isRefreshing.collectAsState().value
    val refreshing = remember {
        mutableStateOf(isRefreshing)
    }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = refreshing.value,
        onRefresh = {
            customIndicatorViewModel.changeRefreshIndicatorStateIndex(3)
            staffListViewModel.refresh()


        },
        refreshThreshold = 90.dp
    )



    LaunchedEffect(key1 = pullRefreshState.progress) {
        when {
            pullRefreshState.progress >= 1 -> {
                customIndicatorViewModel.changeRefreshIndicatorStateIndex(2)
            }

            pullRefreshState.progress > 0 -> {
                customIndicatorViewModel.changeRefreshIndicatorStateIndex(1)
            }
        }
    }

    val state = rememberLazyListState()


    HorizontalPager(state = pagerState) {
        LazyColumn(
            state = state,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 2.dp)
                .padding(horizontal = 16.dp)
                .pullRefresh(pullRefreshState),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            val filteredItems = sortedList.filter { it.firstName.lowercase().startsWith(searchText.lowercase()) ||
                    it.lastName.lowercase().startsWith(searchText.lowercase()) ||
                    it.userTag.lowercase().startsWith(searchText.lowercase()) }



            item {
                CustomIndicatorRefresh(
                    viewModel = customIndicatorViewModel,
                    pullToRefreshProgress = pullRefreshState.progress
                )
            }

            if (!refreshing.value) {
                items(items = filteredItems.filter {

                    if (tabListItems[selectedIndex] == "All") {
                        tabListItems[selectedIndex] == "All"
                    } else {
                        it.department == tabListItems[selectedIndex]
                    }
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
                    items(items = filteredItems.filter {
                        if (tabListItems[selectedIndex] == "All") {
                            tabListItems[selectedIndex] == "All"
                        } else {
                            it.department == tabListItems[selectedIndex]
                        }
                    }) { employee ->
                        if (employee.dateInNextYear) {
                            EmployeeCard(employee, viewModel, navController)

                        }

                    }
                }



            }
            if (filteredItems.isEmpty()) {
                item {
                    NotFound()
                }
            }
        }


    }

}


