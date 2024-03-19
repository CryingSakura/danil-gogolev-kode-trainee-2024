package com.example.stafflist.screen.DetailScreen.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.stafflist.data.Employee

@Composable
fun DetailScreenCap(employee: Employee) {


    Box(modifier = Modifier
        .fillMaxWidth()
        .background(color = MaterialTheme.colorScheme.secondaryContainer)
        .padding(top = 72.dp)) {
        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly) {
            AsyncImage(modifier = Modifier
                .size(104.dp)
                .clip(CircleShape),
                model = employee.avatarUrl,
                contentDescription = employee.avatarUrl.last().toString())

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "${employee.firstName} ${employee.lastName}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer)

                Text(modifier = Modifier.padding(start = 2.dp),
                    text = employee.userTag.lowercase(), fontSize = 17.sp,
                    color = MaterialTheme.colorScheme.surfaceVariant)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = employee.department, fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant)

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}