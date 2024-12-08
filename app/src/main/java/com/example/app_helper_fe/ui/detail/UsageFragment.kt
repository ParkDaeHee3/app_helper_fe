package com.example.app_helper_fe.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.app_helper_fe.databinding.FragmentUsageBinding

class UsageFragment : Fragment() {

    private var _binding: FragmentUsageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 전달받은 데이터 표시
        val usage = arguments?.getString(ARG_USAGE) ?: "정보 없음"
        binding.txtUseMethodQesitm.text = usage
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_USAGE = "useMethodQesitm"

        fun newInstance(usage: String?): UsageFragment {
            val fragment = UsageFragment()
            val args = Bundle()
            args.putString(ARG_USAGE, usage)
            fragment.arguments = args
            return fragment
        }
    }
}