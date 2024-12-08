package com.example.app_helper_fe.ui.tooth

import com.example.app_helper_fe.data.Medicine
import com.example.app_helper_fe.data.Tooth

interface ToothItemClickListener {

    fun onToothClick(medicine: Medicine)
}