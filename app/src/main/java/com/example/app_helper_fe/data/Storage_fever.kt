package com.example.app_helper_fe.data

import com.example.app_helper_fe.R

object Storage_fever {

    val feverList: List<Fever> = getDummyData()

    private fun getDummyData(): List<Fever> {
        return listOf(
            Fever(R.drawable.tylenol, "타이레놀", "Opharmacy", "000-111-222222"),
            Fever(R.drawable.tylenol, "타이레놀", "Npharmacy", "000-111-222222"),
            Fever(R.drawable.tylenol, "타이레놀", "Epharmacy", "000-111-222222"),
            Fever(R.drawable.tylenol, "타이레놀", "Mpharmacy", "000-111-222222"),
            Fever(R.drawable.tylenol, "타이레놀", "Ipharmacy", "000-111-222222"),
            Fever(R.drawable.tylenol, "타이레놀", "Lpharmacy", "000-111-222222"),
            Fever(R.drawable.tylenol, "타이레놀", "Lpharmacy", "000-111-222222"),
            Fever(R.drawable.tylenol, "타이레놀", "Ipharmacy", "000-111-222222"),
            Fever(R.drawable.tylenol, "타이레놀", "Opharmacy", "000-111-222222"),
            Fever(R.drawable.tylenol, "타이레놀", "Npharmacy", "000-111-222222"),
            Fever(R.drawable.ic_home, "타이레놀", "Gpharmacy", "000-111-222222"),
            Fever(R.drawable.ic_arrow_back, "타이레놀", "Opharmacy", "000-111-222222"),
        )
    }


}