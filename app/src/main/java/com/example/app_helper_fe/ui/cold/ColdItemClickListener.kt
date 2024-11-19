package com.example.app_helper_fe.ui.cold

import com.example.app_helper_fe.data.Cold
import com.example.app_helper_fe.data.Medicine

interface ColdItemClickListener {

    fun onColdClick(medicine: Medicine.Body.Item)
}