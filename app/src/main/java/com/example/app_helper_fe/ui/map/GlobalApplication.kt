package com.example.app_helper_fe.ui.map

import android.app.Application
import com.kakao.vectormap.KakaoMapSdk

class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // 다른 초기화 코드들

        // Kakao SDK 초기화
        KakaoMapSdk.init(this, "551a771174283caee21621fbf71f4741")
    }

}