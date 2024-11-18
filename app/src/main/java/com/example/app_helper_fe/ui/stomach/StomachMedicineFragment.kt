package com.example.app_helper_fe.ui.stomach

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.app_helper_fe.data.Stomach
import com.example.app_helper_fe.data.Storage_stomach
import com.example.app_helper_fe.databinding.FragmentStomachMedicineBinding


class StomachMedicineFragment : Fragment(), StomachItemClickListener {

    private var _binding: FragmentStomachMedicineBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStomachMedicineBinding.inflate(inflater, container, false)
        return binding.root
    }

    //여기서 setlayout을 선언 해줘야 뒤로 가기 아이콘 버튼이 활성화 됨
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvStomachMedicineList.adapter = StomachListAdapter(Storage_stomach.stomachList, this)
        setLayout()
    }

    //이동 하는 페이지  기능
    private fun setLayout() {
        setNavigationIcon()
    }

    //위 app nav를 통해 뒤로 가기 기능 구현
    private fun setNavigationIcon() {
        binding.toolbarStomachSection.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onStomachClick(stomach: Stomach) {

    }

}

