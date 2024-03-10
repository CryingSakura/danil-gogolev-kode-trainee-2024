package com.example.stafflist.data

import com.example.stafflist.network.Results
import kotlinx.coroutines.flow.Flow

interface StaffListRepository{
    suspend fun getStaffList(): Flow<Results<List<Employee>>>
}