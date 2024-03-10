package com.example.stafflist.network

import com.example.stafflist.data.StaffListResponse
import retrofit2.http.GET

interface RequestService{


    @GET("users")
    suspend fun getStaffList(): StaffListResponse

    companion object {
        const val  BASE_URL = "https://stoplight.io/mocks/kode-api/trainee-test/331141861"
    }

}