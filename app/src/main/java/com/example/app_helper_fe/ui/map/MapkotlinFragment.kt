package com.example.app_helper_fe.ui.map

//import com.example.app_helper_fe.data.Transfer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.app_helper_fe.KAKAO_MAP_KEY
import com.example.app_helper_fe.databinding.FragmentMapBinding
//import com.example.app_helper_fe.ui.map.MapUtil.getMapActivityIconLabelOptions
//import com.example.app_helper_fe.ui.map.MapUtil.getMapActivityNumberLabelOptions
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.KakaoMapSdk
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView
import com.kakao.vectormap.camera.CameraUpdateFactory
import org.locationtech.proj4j.CRSFactory
import org.locationtech.proj4j.CoordinateTransformFactory
import org.locationtech.proj4j.ProjCoordinate


class MapkotlinFragment {//: Fragment() {
//
//    private val binding get() = _binding!!
//    private var _binding : FragmentMapBinding? = null
//
//    private lateinit var mapView : MapView
//    private var kakaoMap : KakaoMap? = null
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?,
//    ): View? {
//        _binding = FragmentMapBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        var(lat,lon) = transformCoordinates(199151.0872, 273169.6078)
//        Log.e("WHAT THE LAT", lat.toString())
//        Log.e("WHAT THE LON", lon.toString())
//        var ln: LatLng = LatLng.from(lat,lon)
//
//        showMapView()
//
//        val cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from(35.95584569776458, 126.99347915588648))
//        kakaoMap?.moveCamera(cameraUpdate)
//        addMarker(ln, 1)
//    }
//
//
//    private fun transformCoordinates(x: Double, y: Double): Pair<Double, Double> {
//        // CRS 설정
//        val crsFactory = CRSFactory()
//        val sourceCRS = crsFactory.createFromName("EPSG:5174") // TM 중부 좌표계
//        val targetCRS = crsFactory.createFromName("EPSG:4326") // WGS84 (위도/경도)
//
//        // 변환기 생성
//        val transformFactory = CoordinateTransformFactory()
//        val transform = transformFactory.createTransform(sourceCRS, targetCRS)
//
//        // 입력 좌표 (TM)
//        val srcCoord = ProjCoordinate(x, y)
//        val destCoord = ProjCoordinate()
//
//        // 변환 수행
//        transform.transform(srcCoord, destCoord)
//
//        // 결과 반환 (위도, 경도)
//        return Pair(destCoord.y, destCoord.x) // WGS84: (위도, 경도)
//    }
//
//
//    private fun addMarker(latLng: LatLng, activityNumber: Int) {
//        val layer = kakaoMap?.labelManager?.layer
//
//        // IconLabel 추가
//        val iconLabel = layer?.addLabel(
//            getMapActivityIconLabelOptions(latLng, activityNumber)
//        )
//
//        // TextLabel 추가
//        val textLabel = layer?.addLabel(
//            getMapActivityNumberLabelOptions(latLng, activityNumber)
//        )
//
//        // TextLabel의 위치를 IconLabel 내부로 조정
//        if (iconLabel != null && textLabel != null) {
//            // changePixelOffset 메서드를 사용하여 텍스트 라벨의 위치 조정
//            textLabel.changePixelOffset(0f, MapUtil.TEXT_OFFSET_Y)
//        }
//    }
//
//
//    private fun showMapView(){
//
//        mapView = binding.mapView
//
//
//        // KakaoMapSDK 초기화!!
//        KakaoMapSdk.init(requireContext(), KAKAO_MAP_KEY)
//
//        mapView.start(object : MapLifeCycleCallback() {
//
//            override fun onMapDestroy() {
//                // 지도 API가 정상적으로 종료될 때 호출
//                Log.d("KakaoMap", "onMapDestroy")
//            }
//
//            override fun onMapError(p0: Exception?) {
//                // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출
//                Log.e("KakaoMap", "onMapError")
//            }
//        }, object : KakaoMapReadyCallback(){
//            override fun onMapReady(kakaomap: KakaoMap) {
//                // 정상적으로 인증이 완료되었을 때 호출
//                kakaoMap = kakaomap
//            }
//        })
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}