package com.example.kurs.retrofit

import com.example.kurs.retrofit.models.Data
import retrofit2.Call
import retrofit2.http.GET

interface Apiservice {
    @GET("json/")
    fun getdata():Call<List<Data>>
}