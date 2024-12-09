package com.example.app_helper_fe.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.app_helper_fe.R
import com.example.app_helper_fe.databinding.ActivityMedicineDetailBinding
import com.example.app_helper_fe.ui.map.MapFragment
import com.google.android.material.tabs.TabLayout


class MedicineDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMedicineDetailBinding
    private lateinit var adapter: MedicineDetailAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMedicineDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // btnMap 클릭 리스너 추가
        binding.btnmap.setOnClickListener {
            // FragmentTransaction을 통해 MapFragment로 이동
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, MapFragment()) // 전체 화면을 MapFragment로 대체
                .addToBackStack(null) // 뒤로 가기 버튼으로 이전 화면으로 돌아갈 수 있게 설정
                .commit()
        }





        //////임의의 데이터 추가
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


        val itemSeq = intent?.getStringExtra("itemSeq")
        Toast.makeText(this,itemSeq, Toast.LENGTH_SHORT).show()


        //////

        // 전달받은 데이터 처리
        //val medicine = intent.getParcelableExtra<Medicine>("medicine_data")

        medicine?.let { med ->
            // 데이터 설정
            binding.tvMedicineDetailCategory.text = med.diseaseType
            binding.tvMedicineDetailTitle.text = med.name
            loadImage(med.imageUrl)

            // RecyclerView 설정
            adapter = MedicineDetailAdapter(med.details)
            binding.recyclerMedicineDetail.layoutManager = LinearLayoutManager(this)
            binding.recyclerMedicineDetail.adapter = adapter

            // 기본 탭 데이터 표시
            val initialDetails = med.details.filter { it.category == "효능" }
            updateRecyclerView(initialDetails)

            // 탭 리스너 설정
            binding.tabsMedicineDetail.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.let {
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }


    }




    private fun loadImage(url: String) {
        // Glide, Picasso 등 이미지 로딩 라이브러리를 사용
        Glide.with(this)
            .load(url)
            .placeholder(R.drawable.ic_image_not_supported)
            .into(binding.ivMedicineDetailImage)
    }

    private fun updateRecyclerView(filteredDetails: List<MedicineDetail>) {
        adapter.updateData(filteredDetails)
    }
}
