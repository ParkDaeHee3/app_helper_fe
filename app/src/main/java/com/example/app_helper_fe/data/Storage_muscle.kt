package com.example.app_helper_fe.data

import com.example.app_helper_fe.R

object Storage_muscle {

    val muscleList: List<Muscle> = getDummyData()

    private fun getDummyData(): List<Muscle> {
        return listOf(
            Muscle(R.drawable.muscle, "Muscle Relax", "Opharmacy", "000-111-222222"),
            Muscle(R.drawable.muscle, "Muscle Relax", "Npharmacy", "000-111-222222"),
            Muscle(R.drawable.muscle, "Muscle Relax", "Epharmacy", "000-111-222222"),
            Muscle(R.drawable.muscle, "Muscle Relax", "Mpharmacy", "000-111-222222"),
            Muscle(R.drawable.muscle, "Muscle Relax", "Ipharmacy", "000-111-222222"),
            Muscle(R.drawable.muscle, "Muscle Relax", "Lpharmacy", "000-111-222222"),
            Muscle(R.drawable.muscle, "Muscle Relax", "Lpharmacy", "000-111-222222"),
            Muscle(R.drawable.muscle, "Muscle Relax", "Ipharmacy", "000-111-222222"),
            Muscle(R.drawable.muscle, "Muscle Relax", "Opharmacy", "000-111-222222"),
            Muscle(R.drawable.muscle, "Muscle Relax", "Npharmacy", "000-111-222222"),
            Muscle(R.drawable.ic_home, "Muscle Relax", "Gpharmacy", "000-111-222222"),
            Muscle(R.drawable.ic_arrow_back, "Muscle Relax", "Opharmacy", "000-111-222222"),
        )
    }

}