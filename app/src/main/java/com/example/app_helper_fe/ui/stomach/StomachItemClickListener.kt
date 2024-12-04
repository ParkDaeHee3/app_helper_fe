package com.example.app_helper_fe.ui.stomach

import com.example.app_helper_fe.data.Medicine
import com.example.app_helper_fe.data.Stomach

interface StomachItemClickListener {

    fun onStomachClick(medicine: Medicine)
}