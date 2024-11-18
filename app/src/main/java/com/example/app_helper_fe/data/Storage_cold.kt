package com.example.app_helper_fe.data

import com.example.app_helper_fe.R


object Storage_cold {

    val coldList: List<Cold> = getDummyData()

    private fun getDummyData(): List<Cold> {
        return listOf(
            Cold(R.drawable.cold, "판콜", "Opharmacy", "000-111-222222"),
            Cold(R.drawable.cold, "판콜", "Npharmacy", "000-111-222222"),
            Cold(R.drawable.cold, "판콜", "Epharmacy", "000-111-222222"),
            Cold(R.drawable.cold, "판콜", "Mpharmacy", "000-111-222222"),
            Cold(R.drawable.cold, "판콜", "Ipharmacy", "000-111-222222"),
            Cold(R.drawable.cold, "판콜", "Lpharmacy", "000-111-222222"),
            Cold(R.drawable.cold, "판콜", "Lpharmacy", "000-111-222222"),
            Cold(R.drawable.cold, "판콜", "Ipharmacy", "000-111-222222"),
            Cold(R.drawable.cold, "판콜", "Opharmacy", "000-111-222222"),
            Cold(R.drawable.cold, "판콜", "Npharmacy", "000-111-222222"),
            Cold(R.drawable.ic_home, "판콜", "Gpharmacy", "000-111-222222"),
            Cold(R.drawable.ic_arrow_back, "판콜", "Opharmacy", "000-111-222222"),
        )
    }

}