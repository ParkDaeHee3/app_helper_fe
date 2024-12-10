package com.example.app_helper_fe.ui.map;

// 필요한 패키지 및 상수 임포트
import static android.content.Context.LOCATION_SERVICE;
import static android.content.Intent.getIntent;
import static com.example.app_helper_fe.ConstantKt.KAKAO_MAP_KEY;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;

import com.example.app_helper_fe.R;
import com.example.app_helper_fe.data.Pharmacy;
import com.example.app_helper_fe.data.Storage_pharmacy;
import com.example.app_helper_fe.databinding.FragmentMapBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.appbar.MaterialToolbar;
import com.kakao.vectormap.KakaoMap;
import com.kakao.vectormap.KakaoMapReadyCallback;
import com.kakao.vectormap.KakaoMapSdk;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapLifeCycleCallback;
import com.kakao.vectormap.MapView;
import com.kakao.vectormap.camera.CameraUpdate;
import com.kakao.vectormap.camera.CameraUpdateFactory;
import com.kakao.vectormap.label.Label;
import com.kakao.vectormap.label.LabelStyles;
import com.kakao.vectormap.label.LabelTextBuilder;
import com.kakao.vectormap.label.LabelLayer;
import com.kakao.vectormap.label.LabelOptions;
import com.kakao.vectormap.label.LabelStyle;
import com.kakao.vectormap.label.LabelTextStyle;

import org.locationtech.proj4j.CRSFactory;
import org.locationtech.proj4j.CoordinateReferenceSystem;
import org.locationtech.proj4j.CoordinateTransform;
import org.locationtech.proj4j.CoordinateTransformFactory;
import org.locationtech.proj4j.ProjCoordinate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapFragment extends Fragment {

    private FragmentMapBinding binding; // View Binding
    private MapView mapView; // 지도 View
    private KakaoMap kakaoMap; // Kakao 지도 객체
    private double lat; // 현재 위치 위도
    private double lon; // 현재 위치 경도
    private FusedLocationProviderClient fusedLocationProviderClient; // 위치 제공 클라이언트

    private Map<Label, Pharmacy> labelPharmacyMap = new HashMap<>(); // Map to store Label-Pharmacy association

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // View Binding 초기화
        binding = FragmentMapBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // 툴바 설정
        MaterialToolbar toolbar = binding.toolbarBtnBack;
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back); // 뒤로 가기 아이콘 설정
        toolbar.setNavigationOnClickListener(v -> requireActivity().onBackPressed()); // 뒤로 가기 동작

        // FusedLocationProviderClient 초기화
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext());

        // Fragment에서 Intent 데이터 가져오기
        String itemSeq = requireActivity().getIntent().getStringExtra("itemSeq");
        Toast.makeText(requireContext(), itemSeq, Toast.LENGTH_SHORT).show();

        // 현재 위치 가져오기
        getCurrentLocation();

        // Kakao Map 초기화
        mapView = binding.mapView;
        KakaoMapSdk.init(requireContext(), KAKAO_MAP_KEY); // Kakao Map SDK 초기화
        Log.d("MapFragment", "Kakao Map SDK 초기화 완료");

        // MapView의 라이프사이클 콜백 설정
        mapView.start(new MapLifeCycleCallback() {
            @Override
            public void onMapDestroy() {
                Log.d("KakaoMap", "onMapDestroy 호출됨");
            }

            @Override
            public void onMapError(Exception error) {
                Log.e("KakaoMap", "onMapError 발생", error);
            }
        }, new KakaoMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull KakaoMap map) {
                kakaoMap = map;
                Log.d("KakaoMap", "onMapReady 호출됨");

                // 현재 위치로 카메라 이동
                CameraUpdate cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from(lat, lon));
                kakaoMap.moveCamera(cameraUpdate);

                // 마커 스타일 설정
                LabelStyle style = LabelStyle.from(R.drawable.ic_mapmarker_nonselect2 )
                        .setTextStyles(LabelTextStyle.from(37, Color.parseColor("#DB5461"), 2, Color.DKGRAY))
                        .setApplyDpScale(true);

                // 약국 데이터를 지도에 표시
                Storage_pharmacy.INSTANCE.getPharmacyList(Integer.parseInt(itemSeq), pharmacies -> {
                    if (pharmacies != null) {
                        for (Pharmacy pharmacy : pharmacies) {

                            // 마커 생성
                            LabelTextBuilder labelTextBuilder = new LabelTextBuilder();

                            //약품 제고
                            labelTextBuilder.setTexts(String.valueOf(pharmacy.getPharm().getId()));
                            //약품 제고

                            labelTextBuilder.setTexts(pharmacy.getPharm().getName());
                            LabelOptions options = LabelOptions.from(LatLng.from(pharmacy.getPharm().getLat(), pharmacy.getPharm().getLon()))
                                    .setStyles(style)
                                    .setTexts(labelTextBuilder);

                            // 레이블 추가
                            LabelLayer layer = kakaoMap.getLabelManager().getLayer();
                            Label label = layer.addLabel(options);
                            Log.d("LabelAdd", "Label 추가됨: " + label);

                            // 레이블과 약국 데이터 매핑
                            labelPharmacyMap.put(label, pharmacy);
                        }

                        // 지도 클릭 이벤트 처리
                        kakaoMap.setOnLabelClickListener(new KakaoMap.OnLabelClickListener() {

                            @Override
                            public boolean onLabelClicked(KakaoMap kakaoMap, LabelLayer labelLayer, Label clickedLabel) {
                                Log.d("LabelClick", "onLabelClicked 호출됨");
                                // 클릭된 레이블이 labelPharmacyMap에 있는지 확인
                                if (labelPharmacyMap.containsKey(clickedLabel)) {
                                    Pharmacy pharmacy = labelPharmacyMap.get(clickedLabel);
                                    showPharmacyInfo(pharmacy); // 약국 정보 표시
                                   // Toast.makeText(requireContext(), "약품 ID: pharmacy?.medicationIds?.joinToString()", Toast.LENGTH_SHORT).show();
                                    return true; // 클릭 이벤트 처리 완료
                                }
                                return false; // 클릭 이벤트 처리되지 않음
                            }
                        });

                    } else {
                        Log.d("pharmacy", "No pharmacies found");
                    }
                });
            }
        });

        return root;
    }

    /**
     * 현재 위치 정보를 가져오는 메서드
     */
    private void getCurrentLocation() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(location -> {
                        if (location != null) {
                            lat = location.getLatitude();//35.96346701
                            lon = location.getLongitude();//126.9583982;
                            Toast.makeText(requireContext(), "위도: " + lat + ", 경도: " + lon, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(requireContext(), "위치를 가져올 수 없습니다.", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(e -> Toast.makeText(requireContext(),
                            "위치 가져오기 실패: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        }
    }

    /**
     * 좌표 변환 (TM 좌표계 -> WGS84 좌표계)
     */
    public static Pair<Double, Double> transformCoordinates(double x, double y) {
        CRSFactory crsFactory = new CRSFactory();
        String epsg2097 = "+proj=tmerc +lat_0=38 +lon_0=127 +k=1.0 +x_0=200000 +y_0=500000 +ellps=bessel +units=m";
        CoordinateReferenceSystem sourceCRS = crsFactory.createFromParameters("EPSG:2097", epsg2097);
        CoordinateReferenceSystem targetCRS = crsFactory.createFromName("EPSG:4326");

        CoordinateTransformFactory transformFactory = new CoordinateTransformFactory();
        CoordinateTransform transform = transformFactory.createTransform(sourceCRS, targetCRS);

        ProjCoordinate srcCoord = new ProjCoordinate(x, y);
        ProjCoordinate destCoord = new ProjCoordinate();
        transform.transform(srcCoord, destCoord);

        double latOffset = 0.69747461314009;
        double lngOffset = -0.84281021637214;
        double correctedLat = destCoord.y + latOffset;
        double correctedLng = destCoord.x + lngOffset;

        Log.d("CoordinateTransform", "변환된 좌표: " + correctedLat + ", " + correctedLng);
        return new Pair<>(correctedLat, correctedLng);
    }

    /**
     * 약국 정보를 표시하는 메서드
     */
    private void showPharmacyInfo(Pharmacy pharmacy) {
        PharmacyInfoDialog dialog = new PharmacyInfoDialog(requireContext(), pharmacy);
        dialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // View Binding 해제
    }
}
