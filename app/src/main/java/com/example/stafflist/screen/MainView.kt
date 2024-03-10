package com.example.stafflist.screen
import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.TabRow
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stafflist.screen.StaffList.StaffList
import com.example.stafflist.screen.StaffList.StaffListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainView(viewModel: StaffListViewModel){

    var text by remember{ mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val selectedTabIndex by remember { mutableStateOf(0) }


    Scaffold(
        topBar = {
            TopAppBar(title = { /*TODO*/ },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    Row(modifier = Modifier
                        .fillMaxWidth()) {
                        SearchBar(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                            query = text,
                            onQueryChange = {text = it},
                            onSearch = {active = false},
                            active = active,
                            onActiveChange = {active = it},
                            leadingIcon = {
                                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                            },
                            trailingIcon = {
                                if (active){
                                    Icon(modifier = Modifier
                                        .clickable {
                                            if (text.isNotEmpty()){
                                                text = ""
                                            } else{
                                                active = false
                                            }
                                        },
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = "Clear Icon")
                                }
                            }
                        ) {

                        }
                        TabRow(selectedTabIndex = selectedTabIndex) {
                            /*https://www.youtube.com/watch?v=9r4st6dmyNE&ab_channel=PhilippLackner*/
                        }
                    }



                }
            )
        }
    ) {
        StaffList(viewModel = viewModel)
    }
}