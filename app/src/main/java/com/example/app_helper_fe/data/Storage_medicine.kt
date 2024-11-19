package com.example.app_helper_fe.data

import android.util.Log
import com.example.app_helper_fe.R
import com.onemillionlines.payapp.service.MedicineService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Storage_medicine {

    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val medService = retrofit.create(MedicineService::class.java)

    //단일 약품 데이터
    fun getOneData(callback: (List<Medicine.Body.Item>?) -> Unit) {
        medService.getMedicine().enqueue(object : Callback<Medicine> {
            override fun onResponse(call: Call<Medicine>, response: Response<Medicine>) {
                if (response.isSuccessful) {
                    val items = response.body()?.body?.items
                    if (!items.isNullOrEmpty()) {
                        callback(items)
                    } else {
                        Log.d("abcde", "Items are empty")
                        callback(null)
                    }
                } else {
                    Log.d("abcde", "Response error: ${response.errorBody()?.string()}")
                    callback(null)
                }
            }

            override fun onFailure(call: Call<Medicine>, t: Throwable) {
                Log.d("abcde", "Request failed: $t")
                callback(null)
            }
        })
    }

    //감기 데이터
    fun getColdData(callback: (List<Medicine.Body.Item>?) -> Unit) {
        medService.getColdList().enqueue(object : Callback<Medicine> {
            override fun onResponse(call: Call<Medicine>, response: Response<Medicine>) {
                if (response.isSuccessful) {
                    val items = response.body()?.body?.items
                    if (!items.isNullOrEmpty()) {
                        callback(items)
                    } else {
                        Log.d("abcde", "Items are empty")
                        callback(null)
                    }
                } else {
                    Log.d("abcde", "Response error: ${response.errorBody()?.string()}")
                    callback(null)
                }
            }

            override fun onFailure(call: Call<Medicine>, t: Throwable) {
                Log.d("abcde", "Request failed: $t")
                callback(null)
            }
        })
    }
}