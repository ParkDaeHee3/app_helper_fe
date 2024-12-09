package com.example.app_helper_fe.ui.headache

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.app_helper_fe.data.Medicine
import com.example.app_helper_fe.data.Storage_medicine
import com.example.app_helper_fe.databinding.FragmentHeadacheMedicineBinding
import com.example.app_helper_fe.ui.detail.MedicineDetailClickListener

class HeadacheMedicineFragment : Fragment(), HeadacheItemClickListener,MedicineDetailClickListener {

    private var _binding: FragmentHeadacheMedicineBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeadacheMedicineBinding.inflate(inflater, container, false)
        return binding.root
    }

    //여기서 setlayout을 선언 해줘야 뒤로 가기 아이콘 버튼이 활성화 됨
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Storage_medicine.getMedicineListData(1) { medicine ->
            if (medicine != null && medicine.isNotEmpty()) {
                Log.d("final", "Data loaded: ${medicine}")
                binding.rvHeadacheMedicineList.adapter = HeadacheListAdapter(medicine, this, this)
            } else {
                Log.d("final", "No data available or failed to fetch data")
            }
        }
        setLayout()
    }

    //이동 하는 페이지  기능
    private fun setLayout() {
        setNavigationIcon()
    }

    //위 app nav를 통해 뒤로 가기 기능 구현
    private fun setNavigationIcon() {
        binding.toolbarHeadacheSection.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onHeadacheClick(medicine: Medicine) {

    }

    // medicine detail page 약품 상세 정보 이동 기능
    override fun onMedicineDetailClick(id:Int) {
        val itemSeq = id.toString()
        val action = HeadacheMedicineFragmentDirections.actionViewCardHeadacheAreaToMedicineDetail(itemSeq)
        findNavController().navigate(action)

    }


}

