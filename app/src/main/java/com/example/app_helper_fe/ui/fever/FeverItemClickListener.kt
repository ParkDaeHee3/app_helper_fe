package com.example.app_helper_fe.ui.fever

import com.example.app_helper_fe.data.Fever
import com.example.app_helper_fe.data.Medicine

interface FeverItemClickListener {

    fun onFeverClick(medicine: Medicine.Body.Item)
}