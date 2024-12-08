package com.example.app_helper_fe.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.example.app_helper_fe.R
import com.example.app_helper_fe.ui.map.MapFragment

class DetailActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicine_detail2)

        // Toolbar 설정
        val toolbar = findViewById<Toolbar>(R.id.toolbar_medicine_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed()}

        // btnMap 설정
        val btnMap = findViewById<View>(R.id.btn_map)
        btnMap.setOnClickListener {
            // FragmentTransaction을 통해 MapFragment로 이동
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MapFragment()) // 전체 화면을 MapFragment로 대체
                .addToBackStack(null) // 뒤로 가기 버튼으로 이전 화면으로 돌아갈 수 있게 설정
                .commit()
        }



        // ViewPager2와 TabLayout 초기화
        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tab_layout)

        // DetailPagerAdapter 설정
        val intentData = intent
        val adapter = DetailPagerAdapter(this, intentData)
        viewPager.adapter = adapter

        // TabLayout과 ViewPager2 연결
        val tabTitles = listOf("효능", "사용법", "보관 방법", "주의사항")
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }
}
