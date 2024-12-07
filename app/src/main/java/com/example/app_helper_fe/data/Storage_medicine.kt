package com.example.app_helper_fe.data

import android.util.Log
import com.example.app_helper_fe.R
import com.example.app_helper_fe.service.BaseUrl
import com.onemillionlines.payapp.service.MedicineService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URLEncoder


object Storage_medicine {


    val url: String = BaseUrl().baseUrl


    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val medService = retrofit.create(MedicineService::class.java)

    //단일 약품 데이터
    fun getOneData(itemSeq : Int,callback: (Medicine?) -> Unit) {
        medService.getMedicine(itemSeq).enqueue(object : Callback<Medicine> {
            override fun onResponse(call: Call<Medicine>, response: Response<Medicine>) {
                if (response.isSuccessful) {
                    val items = response.body()
                    if (items != null) {
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
    fun getMedicineListData(num: Int,callback: (List<Medicine>?) -> Unit) {
        medService.getMedicineList(num).enqueue(object : Callback<List<Medicine>> {
            override fun onResponse(call: Call<List<Medicine>>, response: Response<List<Medicine>>) {
                if (response.isSuccessful) {
                    val items = response.body()
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

            override fun onFailure(call: Call<List<Medicine>>, t: Throwable) {
                Log.d("abcde", "Request failed: $t")
                callback(null)
            }
        })
    }

    fun getQueryMedicineData(query: String,callback: (List<Medicine>?) -> Unit) {
        val encodedQuery = URLEncoder.encode(query, "UTF-8")
        medService.getQueryMedicineList(encodedQuery).enqueue(object : Callback<List<Medicine>> {
            override fun onResponse(call: Call<List<Medicine>>, response: Response<List<Medicine>>) {
                if (response.isSuccessful) {
                    val items = response.body()
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

            override fun onFailure(call: Call<List<Medicine>>, t: Throwable) {
                Log.d("abcde", "Request failed: $t")
                callback(null)
            }
        })
    }
}