package com.example.app_helper_fe.ui.headache

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.app_helper_fe.data.Headache
import com.example.app_helper_fe.data.Storage_headache
import com.example.app_helper_fe.databinding.FragmentHeadacheMedicineBinding


class HeadacheMedicineFragment : Fragment(), HeadacheItemClickListener {

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
        binding.rvHeadacheMedicineList.adapter =
            HeadacheListAdapter(Storage_headache.headacheList, this)
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


    override fun onHeadacheClick(headache: Headache) {
        /*
        if (Storage_headache.paymentMethod != null) {
            val action = TransferSearchFragmentDirections.actionTransferAccountToTransfer(headache)
            findNavController().navigate(action)
        } else {
            Snackbar.make(
                binding.root,
                getString(R.string.guide_message_add_payment_method),
                Snackbar.LENGTH_LONG
            ).show()
            val action = TransferSearchFragmentDirections.actionTransferAccountToPaymentMethod()
            findNavController().navigate(action)
        }

         */
    }

}

