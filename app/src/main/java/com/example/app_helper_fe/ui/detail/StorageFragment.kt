package com.example.app_helper_fe.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.app_helper_fe.databinding.FragmentStorageBinding

class StorageFragment : Fragment() {

    private var _binding: FragmentStorageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStorageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 전달받은 데이터 표시
        val storage = arguments?.getString(ARG_STORAGE) ?: "정보 없음"
        binding.txtDepositMethodQesitm.text = storage
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_STORAGE = "depositMethodQesitm"

        fun newInstance(storage: String?): StorageFragment {
            val fragment = StorageFragment()
            val args = Bundle()
            args.putString(ARG_STORAGE, storage)
            fragment.arguments = args
            return fragment
        }
    }
}