package com.onemillionlines.payapp.service

import com.example.app_helper_fe.data.Medicine
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MedicineService {
    @GET("/getmedicine")
    fun getMedicine(
        @Query("itemSeq") itemSeq: Int? = null,
    ): Call<List<Medicine>>

    @GET("/getmedicinelist")
    fun getMedicineList(
        @Query("efcy") eff: Int? = null,
    ): Call<List<Medicine>>

    @GET("/getquerymedicine")
    fun getQueryMedicineList(
        @Query("query") query: String? = null,
    ): Call<List<Medicine>>
}