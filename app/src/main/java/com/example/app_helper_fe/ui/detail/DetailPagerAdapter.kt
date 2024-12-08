package com.example.app_helper_fe.ui.detail

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class DetailPagerAdapter(
    activity: FragmentActivity,
    private val intent: Intent
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> EfficacyFragment.newInstance(intent.getStringExtra("efcyQesitm"))
            1 -> UsageFragment.newInstance(intent.getStringExtra("useMethodQesitm"))
            2 -> StorageFragment.newInstance(intent.getStringExtra("depositMethodQesitm"))
            3 -> WarningFragment.newInstance(intent.getStringExtra("atpnQesitm"))
            else -> throw IllegalStateException("Unexpected position: $position")
        }
    }
}