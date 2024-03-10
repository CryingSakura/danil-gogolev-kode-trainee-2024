package com.example.stafflist.screen.StaffList.fragments
import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stafflist.R
import com.example.stafflist.ui.theme.FirstLastNameInCardColor
import com.example.stafflist.ui.theme.SpecialityInCardColor
import com.example.stafflist.ui.theme.TagInCardColor

@Preview(showBackground = true)
@Composable
fun EmployeeCard(
    /*employee: Employee,*/
){
    Row (modifier = Modifier
        .height(80.dp)
        .fillMaxWidth()
        .clickable {  },
        verticalAlignment = Alignment.CenterVertically){

        Box (modifier = Modifier
            .size(width = 88.dp, height =  84.dp),
            contentAlignment = Alignment.CenterStart) {
            Image(modifier = Modifier
                .size(72.dp)
                .clip(CircleShape),
                painter = painterResource(id = R.drawable.zaglushka),
                contentDescription = "test img",
                contentScale = ContentScale.FillBounds)
        }


        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center) {
            Row {
                Text(text = "Alex Minogarov",
                    color = FirstLastNameInCardColor,
                    fontWeight = FontWeight.Bold)
                Text(modifier = Modifier.padding(start = 2.dp),
                    text = "mi",
                    color = TagInCardColor)
            }
            Row {
                Text(text = "Analyst",
                    color = SpecialityInCardColor)
            }
        }
    }
}