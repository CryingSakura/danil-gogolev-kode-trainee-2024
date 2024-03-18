package com.example.stafflist.data

data class Employee(
    val avatarUrl: String = "",
    val birthday: String = "",
    var department: String = "",
    val firstName: String = "",
    val id: String = "",
    val lastName: String = "",
    val phone: String = "",
    val position: String = "",
    val userTag: String = "",
    var dateInNextYear: Boolean = false
)
