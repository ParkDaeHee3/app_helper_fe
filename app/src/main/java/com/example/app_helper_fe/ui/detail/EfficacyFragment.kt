package com.example.app_helper_fe.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.app_helper_fe.R

class EfficacyFragment : Fragment() {

    companion object {
        private const val ARG_EFCY_QESITM = "efcyQesitm"

        fun newInstance(efcyQesitm: String?): EfficacyFragment {
            val fragment = EfficacyFragment()
            val args = Bundle().apply {
                putString(ARG_EFCY_QESITM, efcyQesitm)
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_efficacy, container, false)
        val txtEfcyQesitm = view.findViewById<TextView>(R.id.txt_efcyQesitm)
        txtEfcyQesitm.text = arguments?.getString(ARG_EFCY_QESITM) ?: "효능 정보가 없습니다."
        return view
    }
}
