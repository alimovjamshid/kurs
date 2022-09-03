package com.example.kurs.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    const val BASE_URL="http://cbu.uz/uzc/arkhiv-kursov-valyut/"
    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiservice:Apiservice= getRetrofit().create(Apiservice::class.java)
}