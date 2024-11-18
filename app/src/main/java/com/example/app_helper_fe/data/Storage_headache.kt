package com.example.app_helper_fe.data

import com.example.app_helper_fe.R


object Storage_headache {

    val headacheList: List<Headache> = getDummyData()

    private fun getDummyData(): List<Headache> {
        return listOf(
            Headache(R.drawable.headache, "excedrin", "Opharmacy", "000-111-222222"),
            Headache(R.drawable.headache, "excedrin", "Npharmacy", "000-111-222222"),
            Headache(R.drawable.headache, "excedrin", "Epharmacy", "000-111-222222"),
            Headache(R.drawable.headache, "excedrin", "Mpharmacy", "000-111-222222"),
            Headache(R.drawable.headache, "excedrin", "Ipharmacy", "000-111-222222"),
            Headache(R.drawable.headache, "excedrin", "Lpharmacy", "000-111-222222"),
            Headache(R.drawable.headache, "excedrin", "Lpharmacy", "000-111-222222"),
            Headache(R.drawable.headache, "excedrin", "Ipharmacy", "000-111-222222"),
            Headache(R.drawable.headache, "excedrin", "Opharmacy", "000-111-222222"),
            Headache(R.drawable.headache, "excedrin", "Npharmacy", "000-111-222222"),
            Headache(R.drawable.ic_home, "excedrin", "Gpharmacy", "000-111-222222"),
            Headache(R.drawable.ic_arrow_back, "excedrin", "Opharmacy", "000-111-222222"),
        )
    }


}