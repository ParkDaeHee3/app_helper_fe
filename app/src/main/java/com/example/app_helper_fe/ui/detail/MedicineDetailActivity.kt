package com.example.app_helper_fe.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.app_helper_fe.R
import com.example.app_helper_fe.databinding.ActivityMedicineDetailBinding
import com.example.app_helper_fe.ui.map.MapFragment
import com.google.android.material.tabs.TabLayout

// MedicineDetailActivity: 의약품 상세 정보를 표시하는 화면
class MedicineDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMedicineDetailBinding // 뷰 바인딩 객체
    private lateinit var adapter: MedicineDetailAdapter // RecyclerView 어뎁터

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMedicineDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // btnMap 클릭 리스너 추가
        binding.btnmap.setOnClickListener {
            // FragmentTransaction을 통해 MapFragment로 이동
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container2, MapFragment()) // 전체 화면을 MapFragment로 대체
                .addToBackStack(null) // 뒤로 가기 버튼으로 이전 화면으로 돌아갈 수 있게 설정
                .commit()
        }

        // 의약품 정보 (임시 데이터)
        val medicine = Medicine(
            imageUrl = "https://example.com/medicine.jpg",
            diseaseType = "감기",
            name = "타이레놀",
            details = listOf(
                MedicineDetail("효능", "이 약은 두통 완화에 효과적입니다."),
                MedicineDetail("사용법", "하루에 3회, 식후에 복용하세요."),
                MedicineDetail("보관", "직사광선을 피하고 서늘한 곳에 보관하세요."),
                MedicineDetail("주의", "임산부는 복용 전 의사와 상담하세요.")
            )
        )
        // 전달받은 데이터를 화면에 처리
        //val medicine = intent.getParcelableExtra<Medicine>("medicine_data")

        medicine?.let { med ->
            // 카테고리 및 제목 설정
            binding.tvMedicineDetailCategory.text = med.diseaseType
            binding.tvMedicineDetailTitle.text = med.name
            loadImage(med.imageUrl) // 이미지 로딩

            // RecyclerView 설정
            adapter = MedicineDetailAdapter(med.details)
            binding.recyclerMedicineDetail.layoutManager = LinearLayoutManager(this)
            binding.recyclerMedicineDetail.adapter = adapter

            // 기본 탭 데이터 표시 ("효능" 기준)
            val initialDetails = med.details.filter { it.category == "효능" }
            updateRecyclerView(initialDetails)

            // 탭 리스너 설정
            binding.tabsMedicineDetail.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.let {
                        // 선택된 탭에 따라 데이터 필터링
                        val filteredDetails = when (it.position) {
                            0 -> med.details.filter { detail -> detail.category == "효능" }
                            1 -> med.details.filter { detail -> detail.category == "사용법" }
                            2 -> med.details.filter { detail -> detail.category == "보관" }
                            3 -> med.details.filter { detail -> detail.category == "주의" }
                            else -> med.details
                        }
                        updateRecyclerView(filteredDetails)
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }

        // 툴바 설정
        val toolbar = findViewById<Toolbar>(R.id.toolbar_medicine_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }


    }

    // 이미지 로딩 함수(Glide 사용)
    private fun loadImage(url: String) {
        Glide.with(this)
            .load(url)
            .placeholder(R.drawable.ic_image_not_supported) // 로딩 중 이미지
            .into(binding.ivMedicineDetailImage) // 이미지 뷰에 로드
    }

    // RecyclerView 업데이트 함수
    private fun updateRecyclerView(filteredDetails: List<MedicineDetail>) {
        adapter.updateData(filteredDetails) // 어뎁터 데이터 업데이트
    }
}
