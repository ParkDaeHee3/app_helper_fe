package com.example.app_helper_fe.ui.map;

import static android.content.Context.LOCATION_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;
import static com.example.app_helper_fe.ConstantKt.KAKAO_MAP_KEY;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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
import com.kakao.vectormap.KakaoMap;
import com.kakao.vectormap.KakaoMapReadyCallback;
import com.kakao.vectormap.KakaoMapSdk;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapLifeCycleCallback;
import com.kakao.vectormap.MapView;
import com.kakao.vectormap.camera.CameraUpdate;
import com.kakao.vectormap.camera.CameraUpdateFactory;
import com.kakao.vectormap.label.Label;
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

import java.util.List;

import retrofit2.Retrofit;


public class MapFragment extends Fragment {

    private FragmentMapBinding binding;
    MapView mapView;
    KakaoMap kakaoMap;
    double lat;
    double lon;

    private FusedLocationProviderClient fusedLocationProviderClient;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMapBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // FusedLocationProviderClient 초기화
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext());

        // 위치 정보 가져오기
        getCurrentLocation();

        mapView = binding.mapView;

        KakaoMapSdk.init(requireContext(), KAKAO_MAP_KEY);
        mapView.start(new MapLifeCycleCallback() {
            @Override
            public void onMapDestroy() {
                // 지도 API가 정상적으로 종료될 때 호출
                Log.d("KakaoMap", "onMapDestroy: ");
            }

            @Override
            public void onMapError(Exception error) {
                // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출
                Log.e("KakaoMap", "onMapError: ", error);
            }
        }, new KakaoMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull KakaoMap map) {
                // 정상적으로 인증이 완료되었을 때 호출
                // KakaoMap 객체를 얻어 옵니다.
                kakaoMap = map;

                CameraUpdate cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from(lat,lon));//35.967922, 126.958637));
                kakaoMap.moveCamera(cameraUpdate);

                LabelStyle style = LabelStyle.from(R.drawable.cold)
                        .setTextStyles(LabelTextStyle.from(37, Color.parseColor("#DB5461"), 2, Color.DKGRAY))
                        .setApplyDpScale(true);


                Storage_pharmacy.INSTANCE.getPharmacyList(lat,lon,pharmacies -> {
                    if (pharmacies != null) {
                        for (Pharmacy pharmacy : pharmacies) {
                            Pair<Double,Double> a = transformCoordinates(pharmacy.getLon(),pharmacy.getLat());
                            Log.e("Name", pharmacy.getName());
                            LabelTextBuilder labelTextBuilder = new LabelTextBuilder();
                            labelTextBuilder.setTexts(pharmacy.getName());
                            LabelOptions options = LabelOptions.from(LatLng.from(a.first, a.second))
                                    .setStyles(style).setTexts(labelTextBuilder);

// 3. LabelLayer 가져오기 (또는 커스텀 Layer 생성)
                            LabelLayer layer = kakaoMap.getLabelManager().getLayer();

// 4. LabelLayer 에 LabelOptions 을 넣어 Label 생성하기
                            layer.addLabel(options);
                        }
                    } else {
                        Log.d("pharmacy", "No pharmacies found");
                    }
                });

// 2. LabelOptions 생성하기

            }
        });
        return root;
    }

    //위치 불러오기
    private void getCurrentLocation() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(location -> {
                        if (location != null) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                            Toast.makeText(requireContext(),
                                    "위도: " + latitude + ", 경도: " + longitude,
                                    Toast.LENGTH_SHORT).show();
                            lat = latitude;
                            lon = longitude;
                        } else {
                            Toast.makeText(requireContext(), "위치를 가져올 수 없습니다.", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(e -> Toast.makeText(requireContext(),
                            "위치 가져오기 실패: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show());
        }
    }


    //위도경도 계산기
    public static Pair<Double, Double> transformCoordinates(double x, double y) {
        // CRS 설정
        CRSFactory crsFactory = new CRSFactory();
        String epsg2097 = "+proj=tmerc +lat_0=38 +lon_0=127 +k=1.0 +x_0=200000 +y_0=500000 +ellps=bessel +units=m";
        CoordinateReferenceSystem sourceCRS = crsFactory.createFromParameters("EPSG:2097",epsg2097); // TM 중부 좌표계
        CoordinateReferenceSystem targetCRS = crsFactory.createFromName("EPSG:4326"); // WGS84 (위도/경도)

        // 변환기 생성
        CoordinateTransformFactory transformFactory = new CoordinateTransformFactory();
        CoordinateTransform transform = transformFactory.createTransform(sourceCRS, targetCRS);

        // 입력 좌표 (TM)
        ProjCoordinate srcCoord = new ProjCoordinate(x, y);
        ProjCoordinate destCoord = new ProjCoordinate();

        // 변환 수행
        transform.transform(srcCoord, destCoord);

        // 보정값 계산
        double latOffset = 0.69747461314009;  // 위도 보정값
        double lngOffset = -0.84281021637214; // 경도 보정값

        // 보정 적용
        double correctedLat = destCoord.y + latOffset;
        double correctedLng = destCoord.x + lngOffset;

        Log.e("LONLAT", correctedLat + "::" + correctedLng);

        // 결과 반환 (위도, 경도)
        return new Pair<>(correctedLat, correctedLng); // WGS84: (위도, 경도)
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}