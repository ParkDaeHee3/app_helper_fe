package com.example.app_helper_fe.ui.mypage

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.app_helper_fe.R

class MypageFragment : Fragment(R.layout.fragment_mypage) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 내 정보 버튼 클릭 이벤트
        view.findViewById<Button>(R.id.myinfoBtn)?.setOnClickListener {
            findNavController().navigate(R.id.action_mypageFragment_to_myinfoFragment)
        }


    }
}
