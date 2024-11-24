package com.example.app_helper_fe.data

import com.google.gson.annotations.SerializedName

class Pharmacy (
    @SerializedName("id")
    val id: Int,

    @SerializedName("tel")
    val tel: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("post")
    val post: String,

    @SerializedName("road_post")
    val road_post: String,

    @SerializedName("address")
    val address: String,

    @SerializedName("road_address")
    val road_address: String,

    @SerializedName("lat")
    val lat: String,

    @SerializedName("lon")
    val lon: String

)