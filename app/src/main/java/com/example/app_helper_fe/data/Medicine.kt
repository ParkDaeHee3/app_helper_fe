package com.example.app_helper_fe.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Medicine(
    val medicineResourceId: Int,
    val medicineName: String,
    val pharmacyName: String,
    val pharmacyNumber: String
): Parcelable