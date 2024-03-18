package com.example.stafflist.data
/*DateTimeFormater не работает на 24 API пришлось создать отдельный класс для своих нужд*/
class DateChanger(date: String) {
    val listOfdate = date.split("-")

    val year = listOfdate[0].toInt()
    val month = listOfdate[1].toInt()
    val day = listOfdate[2].toInt()
    private var monthName = ""

    fun returnMonthNameForDate(month: Int): String{
        when(month){
            1 -> monthName = "Января"
            2 -> monthName = "Февраля"
            3 -> monthName = "Марта"
            4 -> monthName = "Апреля"
            5 -> monthName = "Мая"
            6 -> monthName = "Июня"
            7 -> monthName = "Июля"
            8 -> monthName = "Августа"
            9 -> monthName = "Сентября"
            10 -> monthName = "Октября"
            11 -> monthName = "Ноября"
            12 -> monthName = "Декабря"
        }
        return monthName
    }



}