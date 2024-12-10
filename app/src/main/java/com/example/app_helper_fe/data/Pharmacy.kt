package com.example.app_helper_fe.data

import com.google.gson.annotations.SerializedName

class Pharmacy (
    @SerializedName("id")
    val id: Int,

    @SerializedName("pharm")
    val pharm: Pharm,

    @SerializedName("remain")
    val remain: Int

) {
    data class Pharm(
        @SerializedName("id")
        val id: Int,

        @SerializedName("name")
        val name: String,

        @SerializedName("lat")
        val lat: Double,

        @SerializedName("lon")
        val lon: Double,

        @SerializedName("phone")
        val phone: String,

        @SerializedName("daytime")
        val daytime: String,

        @SerializedName("address")
        val address: String
    )
}