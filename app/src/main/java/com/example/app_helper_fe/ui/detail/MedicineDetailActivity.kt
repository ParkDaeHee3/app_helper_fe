package com.example.app_helper_fe.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_helper_fe.databinding.ActivityMedicineDetailBinding
import com.google.android.material.tabs.TabLayout

class MedicineDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMedicineDetailBinding
    private lateinit var adapter: MedicineDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMedicineDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 데이터 생성
        val details = listOf(
            MedicineDetail("효능", "이 약은 두통 완화에 효과적입니다."),
            MedicineDetail("사용법", "하루에 3회, 식후에 복용하세요."),
            MedicineDetail("보관", "직사광선을 피하고 서늘한 곳에 보관하세요."),
            MedicineDetail("주의", "임산부는 복용 전 의사와 상담하세요.")
        )

        // RecyclerView 설정
        adapter = MedicineDetailAdapter(details)
        binding.recyclerMedicineDetail.layoutManager = LinearLayoutManager(this)
        binding.recyclerMedicineDetail.adapter = adapter

        // 탭 선택 리스너 설정
        binding.tabsMedicineDetail.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    val filteredDetails = when (it.position) {
                        0 -> details.filter { detail -> detail.category == "효능" }
                        1 -> details.filter { detail -> detail.category == "사용법" }
                        2 -> details.filter { detail -> detail.category == "보관" }
                        3 -> details.filter { detail -> detail.category == "주의" }
                        else -> details
                    }
                    updateRecyclerView(filteredDetails)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    // RecyclerView 업데이트 함수
    private fun updateRecyclerView(filteredDetails: List<MedicineDetail>) {
        adapter.updateData(filteredDetails)
    }
}
