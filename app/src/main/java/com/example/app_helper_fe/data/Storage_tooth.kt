package com.example.app_helper_fe.data

import com.example.app_helper_fe.R

object Storage_tooth {

    val toothList: List<Tooth> = getDummyData()

    private fun getDummyData(): List<Tooth> {
        return listOf(
            Tooth(R.drawable.tooth, "덴파사", "Opharmacy", "000-111-222222"),
            Tooth(R.drawable.tooth, "덴파사", "Npharmacy", "000-111-222222"),
            Tooth(R.drawable.tooth, "덴파사", "Epharmacy", "000-111-222222"),
            Tooth(R.drawable.tooth, "덴파사", "Mpharmacy", "000-111-222222"),
            Tooth(R.drawable.tooth, "덴파사", "Ipharmacy", "000-111-222222"),
            Tooth(R.drawable.tooth, "덴파사", "Lpharmacy", "000-111-222222"),
            Tooth(R.drawable.tooth, "덴파사", "Lpharmacy", "000-111-222222"),
            Tooth(R.drawable.tooth, "덴파사", "Ipharmacy", "000-111-222222"),
            Tooth(R.drawable.tooth, "덴파사", "Opharmacy", "000-111-222222"),
            Tooth(R.drawable.tooth, "덴파사", "Npharmacy", "000-111-222222"),
            Tooth(R.drawable.ic_home, "덴파사", "Gpharmacy", "000-111-222222"),
            Tooth(R.drawable.ic_arrow_back, "덴파사", "Opharmacy", "000-111-222222"),
        )
    }
}