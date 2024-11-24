package com.example.app_helper_fe.service

import com.example.app_helper_fe.data.Pharmacy
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PharmacyService {

    @GET("/getpharmacy")
    fun getPharmacyList(
        //@Query("query") query: String? = null,
    ): Call<List<Pharmacy>>
}