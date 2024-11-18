package com.example.app_helper_fe.data

import com.example.app_helper_fe.R

object Storage_stomach {

    val stomachList: List<Stomach> = getDummyData()

    private fun getDummyData(): List<Stomach> {
        return listOf(
            Stomach(R.drawable.stomach, "부스코판", "Opharmacy", "000-111-222222"),
            Stomach(R.drawable.stomach, "부스코판", "Npharmacy", "000-111-222222"),
            Stomach(R.drawable.stomach, "부스코판", "Epharmacy", "000-111-222222"),
            Stomach(R.drawable.stomach, "부스코판", "Mpharmacy", "000-111-222222"),
            Stomach(R.drawable.stomach, "부스코판", "Ipharmacy", "000-111-222222"),
            Stomach(R.drawable.stomach, "부스코판", "Lpharmacy", "000-111-222222"),
            Stomach(R.drawable.stomach, "부스코판", "Lpharmacy", "000-111-222222"),
            Stomach(R.drawable.stomach, "부스코판", "Ipharmacy", "000-111-222222"),
            Stomach(R.drawable.stomach, "부스코판", "Opharmacy", "000-111-222222"),
            Stomach(R.drawable.stomach, "부스코판", "Npharmacy", "000-111-222222"),
            Stomach(R.drawable.ic_home, "부스코판", "Gpharmacy", "000-111-222222"),
            Stomach(R.drawable.ic_arrow_back, "부스코판", "Opharmacy", "000-111-222222"),
        )
    }


}