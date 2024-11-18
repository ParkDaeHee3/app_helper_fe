package com.example.app_helper_fe.data

import com.example.app_helper_fe.R


object Storage_bruise {

    val bruiseList: List<Bruise> = getDummyData()
    private fun getDummyData(): List<Bruise> {
        return listOf(
            Bruise(R.drawable.bruise, "베노플러스", "Opharmacy", "000-111-222222"),
            Bruise(R.drawable.bruise, "베노플러스", "Npharmacy", "000-111-222222"),
            Bruise(R.drawable.bruise, "베노플러스", "Epharmacy", "000-111-222222"),
            Bruise(R.drawable.bruise, "베노플러스", "Mpharmacy", "000-111-222222"),
            Bruise(R.drawable.bruise, "베노플러스", "Ipharmacy", "000-111-222222"),
            Bruise(R.drawable.bruise, "베노플러스", "Lpharmacy", "000-111-222222"),
            Bruise(R.drawable.bruise, "베노플러스", "Lpharmacy", "000-111-222222"),
            Bruise(R.drawable.bruise, "베노플러스", "Ipharmacy", "000-111-222222"),
            Bruise(R.drawable.bruise, "베노플러스", "Opharmacy", "000-111-222222"),
            Bruise(R.drawable.bruise, "베노플러스", "Npharmacy", "000-111-222222"),
            Bruise(R.drawable.ic_home, "베노플러스", "Gpharmacy", "000-111-222222"),
            Bruise(R.drawable.ic_arrow_back, "베노플러스", "Opharmacy", "000-111-222222"),
        )
    }
}