package com.example.app_helper_fe.ui.map

import android.graphics.Color
import com.example.app_helper_fe.R
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles
import com.kakao.vectormap.label.LabelTextStyle

object MapUtil {
//    const val DEFAULT_ZOOM_LEVEL = 10 // 루트를 표시하는 기본 줌 레벨
//
//    private const val RANK_INTERVAL = 10 // activity 번호에 따른 rank 차이 (더 높은 activityNumber를 가졌다면 핀을 더 위에 표시)
//    private const val RANK_OFFSET = 1 // 아이콘-텍스트 간 rank 차이 (기본적으로 텍스트는 아이콘 위에 표시)
//
//    private const val ICON_SIZE = 60f // IconLabel의 크기를 가정
//    const val TEXT_OFFSET_Y = - (ICON_SIZE / (2.3)).toFloat() // 텍스트를 이동할 offset (아이콘 중심에서 약간 위로 이동)
//
//    /** 스타일 관련 */
//    // IconLabel
////    private fun setMapIconLabelStyles(category: Category): LabelStyles {
////        return LabelStyles.from(
////            LabelStyle.from(category.categoryMarkerIcon)
////        )
////    }
//
//    fun getMapActivityIconLabelOptions(latLng: LatLng, activityNumber: Int): LabelOptions {
//        return LabelOptions.from(latLng)
//            .setStyles(R.drawable.ic_map)
//            .setRank((activityNumber * RANK_INTERVAL).toLong()) // activityNumber가 클수록 높은 rank를 가짐
//    }
//
//    // TextLabel
//    private fun setMapTextLabelStyle(): LabelStyles {
//        return LabelStyles.from(
//            LabelStyle.from(LabelTextStyle.from(28, Color.WHITE))
//        )
//    }
//
//    fun getMapActivityNumberLabelOptions(latLng: LatLng, activityNumber: Int): LabelOptions {
//        return LabelOptions.from(latLng)
//            .setStyles(setMapTextLabelStyle())
//            .setTexts("아브라카다브라")
//            .setRank((activityNumber * RANK_INTERVAL + RANK_OFFSET).toLong()) // 텍스트는 아이콘보다 높은 rank를 가짐
//    }
}