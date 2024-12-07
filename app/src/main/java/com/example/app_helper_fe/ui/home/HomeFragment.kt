package com.example.app_helper_fe.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.app_helper_fe.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), MapClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    //Destination 이동 (라이브러리 활용)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayout()
    }

    private fun setLayout() {
        //setMapCard() // map destination 지도로 이동
        setColdCard() //cold destination 감기 카드 목록 이동
        setHeadacheCard()// headache destination 두통 카드 목록 이동
        setMuscleCard()//muscle destination 근육통 카드 목록 이동
        setBruiseCard()//bruise destination 타박상 카드 목록 이동
        setStomachCard()//stomach destination 복통 카드 목록 이동
        setFeverCard() //fever destination 온열 카드 목록 이동
        setToothCard() // tooth destination 치통 카드 목록 이동
    }

//감기 카드 섹션 이동
//private fun setMapCard() {
//    with(binding) {
//        groupAddCard.visibility = View.VISIBLE
//        ivViewCardMap.setOnClickListener {
//            val action = HomeFragmentDirections.actionHomeToMap()
//            findNavController().navigate(action)
//       }
//    }
//}


    //감기 카드 섹션 이동
    private fun setColdCard() {
        with(binding) {
            groupAddCard.visibility = View.VISIBLE
            viewCardColdArea.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeToViewCardColdArea()
                findNavController().navigate(action)
            }
        }
    }

    //두통 카드 섹션 이동
    private fun setHeadacheCard() {
        with(binding) {
            groupAddCard.visibility = View.VISIBLE
            viewCardHeadacheArea.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeToViewCardHeadacheArea()
                findNavController().navigate(action)
            }
        }
    }

    //근육통 카드 섹션 이동
    private fun setMuscleCard() {
        with(binding) {
            groupAddCard.visibility = View.VISIBLE
            viewCardMuscleArea.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeToViewCardMuscleArea()
                findNavController().navigate(action)
            }
        }
    }

    //타박상 카드 섹션 이동
    private fun setBruiseCard() {
        with(binding) {
            groupAddCard.visibility = View.VISIBLE
            viewCardBruiseArea.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeToViewCardBruiseArea()
                findNavController().navigate(action)
            }
        }
    }

    //복통 카드 섹션 이동
    private fun setStomachCard() {
        with(binding) {
            groupAddCard.visibility = View.VISIBLE
            viewCardStomachArea.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeToViewCardStomachArea()
                findNavController().navigate(action)
            }
        }
    }

    //복통 카드 섹션 이동
    private fun setFeverCard() {
        with(binding) {
            groupAddCard.visibility = View.VISIBLE
            viewCardFeverArea.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeToViewCardFeverArea()
                findNavController().navigate(action)
            }
        }
    }

    //치통 카드 섹션 이동
    private fun setToothCard() {
        with(binding) {
            groupAddCard.visibility = View.VISIBLE
            viewCardToothArea.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeToViewCardToothArea()
                findNavController().navigate(action)
            }
        }
    }


    //메모리 관리를 위해서
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}