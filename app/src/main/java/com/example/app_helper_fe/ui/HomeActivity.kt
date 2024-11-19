package com.example.app_helper_fe.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.app_helper_fe.R
import com.example.app_helper_fe.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    //view binding
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBottomNavigation()
    }

    //하단바를 통한 이동 (R.id을 통해 이동함), 그리고 navigation 라이브러리를 통해 bottom_nav와 연동해서 동작함
    private fun setBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container_home) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationHome.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.navigation_home, R.id.navigation_search_medicine,R.id.navigation_map,R.id.navigation_maypage-> {
                    binding.bottomNavigationHome.visibility = View.VISIBLE
                }
                else -> {
                    binding.bottomNavigationHome.visibility = View.VISIBLE
                }
            }
        }
    }
}