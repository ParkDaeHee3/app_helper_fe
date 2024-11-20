package com.example.app_helper_fe.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.app_helper_fe.R
import com.example.app_helper_fe.databinding.ActivityMedicineDetailBinding

class MedicineDetailActivity: AppCompatActivity() {

    //view binding
    private lateinit var binding: ActivityMedicineDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMedicineDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


}