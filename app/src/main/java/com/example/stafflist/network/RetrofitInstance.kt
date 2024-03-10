package com.example.stafflist.network
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance{
    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level =HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

    val requestService: RequestService = Retrofit
        .Builder()
        .baseUrl(RequestService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(RequestService::class.java)
}