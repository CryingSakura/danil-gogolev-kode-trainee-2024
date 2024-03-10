package com.example.stafflist.network

sealed class Results<T>(
    val data: T? = null,
    val message: String? = null
){
    class Success<T>(data: T?): Results<T>(data)
    class Error<T>(data: T? = null, message: String): Results<T>(data, message)
}