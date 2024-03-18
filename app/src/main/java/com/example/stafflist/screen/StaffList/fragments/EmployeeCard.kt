package com.example.stafflist.screen.StaffList.fragments
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.stafflist.MainActivityViewModel
import com.example.stafflist.data.Employee
import com.example.stafflist.navigation.Graphs
import com.example.stafflist.ui.theme.FirstLastNameInCardColor
import com.example.stafflist.ui.theme.SpecialityInCardColor
import com.example.stafflist.ui.theme.TagInCardColor

@Composable
fun EmployeeCard(
    employee: Employee,
    viewModel: MainActivityViewModel,
    navController: NavController
){

    val detailScreenViewModel = viewModel.detailSreenViewModel




    val showShimmer = remember { mutableStateOf(true) }

    Row (modifier = Modifier
        .height(80.dp)
        .fillMaxWidth()
        .clickable {
            detailScreenViewModel.fetchEmployee(employee)
            navController.navigate(Graphs.DETAILSCREEN)
        },
        verticalAlignment = Alignment.CenterVertically){

        Box (modifier = Modifier
            .size(width = 88.dp, height =  84.dp),
            contentAlignment = Alignment.CenterStart) {
            AsyncImage(modifier = Modifier
                .size(72.dp)
                .clip(CircleShape),
                model = employee.avatarUrl,
                contentDescription = employee.id,
                contentScale = ContentScale.FillBounds,
                onSuccess = { showShimmer.value = false })
        }


        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center) {
            Row {
                Text(text = "${employee.firstName} ${employee.lastName}",
                    color = FirstLastNameInCardColor,
                    fontWeight = FontWeight.Bold)
                Text(modifier = Modifier
                    .padding(start = 2.dp),
                    text = employee.userTag.lowercase(),
                    color = TagInCardColor)
            }
            Row {
                Text(text = employee.department,
                    color = SpecialityInCardColor)
            }
        }
    }
}