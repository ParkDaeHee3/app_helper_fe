package com.example.app_helper_fe.ui.detail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Medicine(
    var imageUrl: String,
    var diseaseType: String,
    var name: String,
    var details: List<MedicineDetail>
) : Parcelable

@Parcelize
data class MedicineDetail(
    var category: String,
    var content: String
) : Parcelable
