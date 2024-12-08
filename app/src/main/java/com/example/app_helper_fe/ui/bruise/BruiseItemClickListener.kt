package com.example.app_helper_fe.ui.bruise

import com.example.app_helper_fe.data.Bruise
import com.example.app_helper_fe.data.Medicine

interface BruiseItemClickListener {

    fun onBruiseClick(medicine: Medicine)
}