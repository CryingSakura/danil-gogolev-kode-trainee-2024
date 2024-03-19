package com.example.stafflist.screen.DetailScreen

import androidx.lifecycle.ViewModel
import com.example.stafflist.data.Employee
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Calendar

class DetailSreenViewModel: ViewModel(){

    private val _stateEmployee = MutableStateFlow(Employee())
    val stateEmployee = _stateEmployee.asStateFlow()




    fun fullYearDeclinationTextReturn(fullYear: Int): String{
        val trueDeclination: String
        val fullYearLastNum = fullYear.toString().last().toString().toInt()
        trueDeclination = if (fullYear < 21 && fullYear > 4 || (fullYear >= 21 && (fullYearLastNum >= 5 || fullYearLastNum == 0))){
            "Лет"
        }else if(fullYearLastNum == 1){
            "Год"
        }else{
            "Года"
        }
        return trueDeclination
    }

    fun calcFullYear(year: Int, month: Int, day: Int): Int {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        return if (month < currentMonth){
            currentYear - year
        } else if (month == currentMonth){
            if (day >= currentDay){
                currentYear - year
            } else{
                currentYear - year - 1
            }
        }else{
            currentYear - year - 1
        }
    }





    fun fetchEmployee(dataForDetailScreen: Employee){
        _stateEmployee.update {it.copy(
            avatarUrl = dataForDetailScreen.avatarUrl,
            birthday = dataForDetailScreen.birthday,
            department = dataForDetailScreen.department,
            firstName = dataForDetailScreen.firstName,
            id = dataForDetailScreen.id,
            lastName = dataForDetailScreen.lastName,
            phone = dataForDetailScreen.phone,
            position = dataForDetailScreen.position,
            userTag = dataForDetailScreen.userTag
        )
        }
    }
}