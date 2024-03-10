package com.example.stafflist.network

import com.example.stafflist.data.Employee
import com.example.stafflist.data.StaffListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class StaffListRepImplementation(
    private val requestService: RequestService
): StaffListRepository{
    override suspend fun getStaffList(): Flow<Results<List<Employee>>> {
        return flow {
            val staffsFormApi = try {
                requestService.getStaffList()
            }catch (e: IOException){
                e.printStackTrace()
                emit(Results.Error(message = "Error loading staff list"))
                return@flow
            }catch (e: HttpException){
                e.printStackTrace()
                emit(Results.Error(message = "Error loading staff list"))
                return@flow
            }
            catch (e: Exception){
                e.printStackTrace()
                emit(Results.Error(message = "Error loading staff list"))
                return@flow
            }
            emit(Results.Success(staffsFormApi.items))
        }
    }

}