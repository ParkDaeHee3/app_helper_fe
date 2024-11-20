package com.example.app_helper_fe.ui.headache

import com.example.app_helper_fe.data.Headache
import com.example.app_helper_fe.data.Medicine

interface HeadacheItemClickListener {

    fun onHeadacheClick(medicine: Medicine.Body.Item)
}