package com.example.app_helper_fe.data

import android.util.Log
import com.example.app_helper_fe.service.BaseUrl
import com.example.app_helper_fe.service.PharmacyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun interface PharmacyListCallback {
    fun onResult(pharmacies: List<Pharmacy>?)
}

object Storage_pharmacy {
    val url: String = BaseUrl().baseUrl

    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val phaService = retrofit.create(PharmacyService::class.java)

    // 단일 약품 데이터
    fun getPharmacyList(callback: PharmacyListCallback) {
        phaService.getPharmacyList().enqueue(object : Callback<List<Pharmacy>> {
            override fun onResponse(call: Call<List<Pharmacy>>, response: Response<List<Pharmacy>>) {
                if (response.isSuccessful) {
                    val items = response.body()
                    if (!items.isNullOrEmpty()) {
                        callback.onResult(items)
                    } else {
                        Log.d("abcde", "Items are empty")
                        callback.onResult(null)
                    }
                } else {
                    Log.d("abcde", "Response error: ${response.errorBody()?.string()}")
                    callback.onResult(null)
                }
            }

            override fun onFailure(call: Call<List<Pharmacy>>, t: Throwable) {
                Log.d("abcde", "Request failed: $t")
                callback.onResult(null)
            }
        })
    }
}
