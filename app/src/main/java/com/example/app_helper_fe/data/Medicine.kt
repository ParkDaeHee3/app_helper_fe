package com.example.app_helper_fe.data

import com.google.gson.annotations.SerializedName


data class Medicine(
    @SerializedName("id")
    val id: Int,

    @SerializedName("itemName")
    val itemName: String,

    @SerializedName("entpName")
    val entpName: String,

    @SerializedName("efcyQesitm")
    val efcyQesitm: String,

    @SerializedName("useMethod")
    val useMethod: String,

    @SerializedName("atpnQesitm")
    val atpnQesitm: String,

    @SerializedName("intrcQesitm")
    val intrcQesitm: String,

    @SerializedName("seQesitm")
    val seQesitm: String,

    @SerializedName("depositMethod")
    val depositMethod: String,

    @SerializedName("itemImage")
    val itemImage: String,

)
//    @SerializedName("header")
//    val header: Header,
//
//    @SerializedName("body")
//    val body: Body
//) {
//    data class Header(
//        @SerializedName("resultCode")
//        val resultCode: String,
//
//        @SerializedName("resultMsg")
//        val resultMsg: String
//    )
//
//    data class Body(
//        @SerializedName("pageNo")
//        val pageNo: Int,
//
//        @SerializedName("totalCount")
//        val totalCount: Int,
//
//        @SerializedName("numOfRows")
//        val numOfRows: Int,
//
//        @SerializedName("items")
//        val items: List<Item>
//    ) {
//        data class Item(
//            @SerializedName("entpName")
//            val entpName: String,
//
//            @SerializedName("itemName")
//            val itemName: String,
//
//            @SerializedName("itemSeq")
//            val itemSeq: String,
//
//            @SerializedName("efcyQesitm")
//            val efcyQesitm: String?,
//
//            @SerializedName("useMethodQesitm")
//            val useMethodQesitm: String?,
//
//            @SerializedName("atpnWarnQesitm")
//            val atpnWarnQesitm: String?,
//
//            @SerializedName("atpnQesitm")
//            val atpnQesitm: String?,
//
//            @SerializedName("intrcQesitm")
//            val intrcQesitm: String?,
//
//            @SerializedName("seQesitm")
//            val seQesitm: String?,
//
//            @SerializedName("depositMethodQesitm")
//            val depositMethodQesitm: String?,
//
//            @SerializedName("openDe")
//            val openDe: String?,
//
//            @SerializedName("updateDe")
//            val updateDe: String?,
//
//            @SerializedName("itemImage")
//            val itemImage: String?,
//
//            @SerializedName("bizrno")
//            val bizrno: String?
//        )
//    }
//}