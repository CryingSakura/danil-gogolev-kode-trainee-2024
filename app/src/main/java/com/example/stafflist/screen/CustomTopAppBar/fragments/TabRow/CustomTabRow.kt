package com.example.stafflist.screen.CustomTopAppBar.fragments.TabRow

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.stafflist.ui.theme.MainTextColor
import com.example.stafflist.ui.theme.SelectedIndicatorColor
import com.example.stafflist.ui.theme.UnSelectedTabTextColor

@Composable
fun CustomTabRows(viewModel: TabRowViewModel){

    val tabTitles = TabItems()

    val selectedIndex = viewModel.selectedIndex.collectAsState().value


    ScrollableTabRow(modifier = Modifier.fillMaxWidth(),
        selectedTabIndex = selectedIndex,
        indicator = {tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[selectedIndex]),
                color = SelectedIndicatorColor

            )
        }) {
        tabTitles.tabListItems.forEachIndexed { index, title ->
            Tab(selected = index == selectedIndex,
                onClick = {
                    viewModel.updateIndex(index)
                },
                text = {
                    Text(text = title,
                        color = if(index == selectedIndex){
                            MainTextColor
                        } else{
                            UnSelectedTabTextColor
                            },
                        fontSize = 16.sp)
                })
        }
    }



}

data class TabItems(
    val tabListItems: List<String> = listOf(
        "All",
        "Designers",
        "Analysts",
        "Managers",
        "iOS",
        "Android",
        "QA",
        "HR",
        "PR",
        "Frontend",
        "Backend",
        "Support",
        "Back-Office",
    )
)


/*
android -> Android
ios -> iOS
design -> Дизайн
management -> Менеджмент
qa -> QA
back_office -> Бэк-офис
frontend -> Frontend
hr -> HR
pr -> PR
backend -> Backend
support -> Техподдержка
analytics -> Аналитика*/
