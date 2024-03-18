package com.example.stafflist.screen.DetailSreen.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stafflist.MainActivityViewModel
import com.example.stafflist.data.DateChanger
import com.example.stafflist.data.Employee


@Composable
fun DetailScreenBody(ctx: Context, employee: Employee, viewModel: MainActivityViewModel){

    val detailSreenViewModel = viewModel.detailSreenViewModel

    val uri = Uri.parse("tel:+7 ${employee.phone}")



    val date = DateChanger(employee.birthday)

    val intent = Intent(Intent.ACTION_DIAL, uri)

    val fullYear = detailSreenViewModel.calcFullYear(date.year, date.month, date.day)

    val yearDeclination = detailSreenViewModel.fullYearDeclinationTextReturn(fullYear)


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)
        .padding(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Icon(modifier = Modifier
                .size(20.dp),
                imageVector = Icons.Outlined.Star, contentDescription = null, tint = Color.Black)

            Spacer(modifier = Modifier.width(14.dp))

            Text(text = "${date.day} ${date.returnMonthNameForDate(date.month)} ${date.year}", fontSize = 16.sp, color = Color.Black)

            Spacer(modifier = Modifier.weight(1f))

            Text(text = "$fullYear $yearDeclination", fontSize = 16.sp, color = Color.Black)
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
            verticalAlignment = Alignment.CenterVertically) {

            Icon(modifier = Modifier
                .size(20.dp),
                imageVector = Icons.Outlined.Call, contentDescription = null, tint = Color.Black)

            Spacer(modifier = Modifier.width(14.dp))

            Text(modifier = Modifier.clickable { ctx.startActivity(intent) },
                text = "+7 ${employee.phone}", fontSize = 16.sp, color = Color.Black)


        }
    }
}