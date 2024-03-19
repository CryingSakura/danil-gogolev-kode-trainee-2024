package com.example.stafflist.screen.StaffList.fragments
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.stafflist.MainActivityViewModel
import com.example.stafflist.data.DateChanger
import com.example.stafflist.data.Employee
import com.example.stafflist.navigation.Graphs
@Composable
fun EmployeeCard(
    employee: Employee,
    viewModel: MainActivityViewModel,
    navController: NavController
){

    val detailScreenViewModel = viewModel.detailSreenViewModel
    val sortViewModel = viewModel.sortViewModel
    
    val sortId = sortViewModel.indexOfSelectedItem.collectAsState().value

    val date = DateChanger(employee.birthday)

    val month = if (date.month == 5){
        "май"
    }else{
        date.returnMonthNameForDate(date.month).lowercase().take(3)
    }


    Row (modifier = Modifier
        .height(80.dp)
        .fillMaxWidth()
        .clickable {
            detailScreenViewModel.fetchEmployee(employee)
            navController.navigate(Graphs.DETAILSCREEN) { popUpTo(Graphs.MAINVIEW) }
        },
        verticalAlignment = Alignment.CenterVertically){

        Box (modifier = Modifier
            .size(width = 88.dp, height =  80.dp),
            contentAlignment = Alignment.CenterStart) {
            AsyncImage(modifier = Modifier
                .size(72.dp)
                .clip(CircleShape),
                model = employee.avatarUrl,
                contentDescription = employee.id,
                contentScale = ContentScale.FillBounds)
        }


        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center) {
            Row (verticalAlignment = Alignment.CenterVertically){
                Text(text = "${employee.firstName} ${employee.lastName}",
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp)
                Text(modifier = Modifier
                    .padding(start = 2.dp),
                    text = employee.userTag.lowercase(),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    fontSize = 14.sp)
                Spacer(modifier = Modifier.weight(1f))
                if (sortId == 1) Text(text = "${date.day} $month",
                    fontSize = 15.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            Row(modifier = Modifier.padding(2.dp)) {
                Text(text = employee.department,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 13.sp)
            }
        }
    }
}