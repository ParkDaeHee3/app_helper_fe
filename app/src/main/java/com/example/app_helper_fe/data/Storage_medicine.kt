package com.example.app_helper_fe.data

import com.example.app_helper_fe.R


object Storage_medicine {

    val medicineList: List<Medicine> = getDummyData()

    private fun getDummyData(): List<Medicine> {
        return listOf(
            Medicine(R.drawable.tylenol, "tylenol1", "Obank", "000-111-222222"),
            Medicine(R.drawable.tylenol, "tylenol2", "Nbank", "000-111-222222"),
            Medicine(R.drawable.tylenol, "tylenol_3", "Ebank", "000-111-222222"),
            Medicine(R.drawable.tylenol, "tylenol_4", "Mbank", "000-111-222222"),
            Medicine(R.drawable.tylenol, "tylenol_5", "Ibank", "000-111-222222"),
            Medicine(R.drawable.tylenol, "tylenol_6", "Lbank", "000-111-222222"),
            Medicine(R.drawable.tylenol, "tylenol_7", "Lbank", "000-111-222222"),
            Medicine(R.drawable.tylenol, "tylenol_8", "Ibank", "000-111-222222"),
            Medicine(R.drawable.tylenol, "tylenol_9", "Obank", "000-111-222222"),
            Medicine(R.drawable.tylenol, "tylenol_10", "Nbank", "000-111-222222"),
            Medicine(R.drawable.ic_home, "home", "Gbank", "000-111-222222"),
            Medicine(R.drawable.ic_arrow_back, "back", "Obank", "000-111-222222"),
        )
    }
}