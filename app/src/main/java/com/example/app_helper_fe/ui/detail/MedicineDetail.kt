package com.example.app_helper_fe.ui.detail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Medicine(
    val imageUrl: String,
    val diseaseType: String,
    val name: String,
    val details: List<MedicineDetail>
) : Parcelable

@Parcelize
data class MedicineDetail(
    val category: String,
    val content: String
) : Parcelable
