package com.example.app_helper_fe.ui.map;

import static com.example.app_helper_fe.ConstantKt.KAKAO_MAP_KEY;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.app_helper_fe.R;
import com.example.app_helper_fe.data.Pharmacy;
import com.example.app_helper_fe.data.Storage_pharmacy;
import com.example.app_helper_fe.databinding.FragmentMapBinding;
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

    Storage_pharmacy storagePharmacy;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMapBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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

                CameraUpdate cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from(35.963727, 126.959107));
                kakaoMap.moveCamera(cameraUpdate);

                LabelStyle style = LabelStyle.from(R.drawable.ic_map)
                        .setTextStyles(LabelTextStyle.from(37, Color.parseColor("#DB5461"), 2, Color.DKGRAY))
                        .setApplyDpScale(true);


                Storage_pharmacy.INSTANCE.getPharmacyList(pharmacies -> {
                    if (pharmacies != null) {
                        for (Pharmacy pharmacy : pharmacies) {
                            for(int i = 0; i < 10; i++) {
                                Pair<Double,Double> a = transformCoordinates(Double.parseDouble(pharmacy.getLat()),Double.parseDouble(pharmacy.getLon()));
                                LabelTextBuilder labelTextBuilder = new LabelTextBuilder();
                                labelTextBuilder.setTexts("신동 A 약국");
                                LabelOptions options = LabelOptions.from(LatLng.from(a.first, a.second))
                                        .setStyles(style).setTexts(labelTextBuilder);

    // 3. LabelLayer 가져오기 (또는 커스텀 Layer 생성)
                                LabelLayer layer = kakaoMap.getLabelManager().getLayer();

    // 4. LabelLayer 에 LabelOptions 을 넣어 Label 생성하기
                                layer.addLabel(options);
                            }
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

    public static Pair<Double, Double> transformCoordinates(double x, double y) {
        // CRS 설정
        CRSFactory crsFactory = new CRSFactory();
        CoordinateReferenceSystem sourceCRS = crsFactory.createFromName("EPSG:5174"); // TM 중부 좌표계
        CoordinateReferenceSystem targetCRS = crsFactory.createFromName("EPSG:4326"); // WGS84 (위도/경도)

        // 변환기 생성
        CoordinateTransformFactory transformFactory = new CoordinateTransformFactory();
        CoordinateTransform transform = transformFactory.createTransform(sourceCRS, targetCRS);

        // 입력 좌표 (TM)
        ProjCoordinate srcCoord = new ProjCoordinate(x, y);
        ProjCoordinate destCoord = new ProjCoordinate();

        // 변환 수행
        transform.transform(srcCoord, destCoord);

        // 결과 반환 (위도, 경도)
        return new Pair<>(destCoord.y, destCoord.x); // WGS84: (위도, 경도)
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}