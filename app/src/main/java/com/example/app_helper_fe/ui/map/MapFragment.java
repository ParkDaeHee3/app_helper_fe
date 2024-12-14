package com.example.app_helper_fe.ui.map;

// 필요한 패키지 및 상수 임포트
import static android.content.Context.LOCATION_SERVICE;
import static android.content.Intent.getIntent;
import static com.example.app_helper_fe.ConstantKt.KAKAO_MAP_KEY;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
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
import com.kakao.vectormap.mapwidget.InfoWindowOptions;
import com.kakao.vectormap.mapwidget.component.GuiImage;
import com.kakao.vectormap.mapwidget.component.GuiLayout;
import com.kakao.vectormap.mapwidget.component.GuiText;
import com.kakao.vectormap.mapwidget.component.Orientation;

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

    private Bitmap createCustomMarker(String text) {
        Drawable drawable = ContextCompat.getDrawable(requireContext().getApplicationContext(), R.drawable.ic_mapmarker_nonclicked);
        if (drawable == null) {
            Log.e("CustomMarker", "마커 이미지를 로드하지 못했습니다.");
            return null;
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setTextSize(50);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);

        canvas.drawText(text, canvas.getWidth() / 2, canvas.getHeight() / 2, paint);
        return bitmap;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // View Binding 초기화
        binding = FragmentMapBinding.inflate(inflater, container, false);
        // <include> 태그로 추가된 레이아웃은 binding.bottomInfoCard가 LayoutBottomInfoCardBinding 타입일 수 있음


        // <include> 태그로 추가된 레이아웃은 LayoutBottomInfoCardBinding 타입으로 생성됩니다.
        // getRoot()를 사용하여 루트 View를 가져옵니다.
        View bottomCard = binding.bottomInfoCard.getRoot();

        // 기본적으로 숨김 처리
        bottomCard.setVisibility(View.GONE);

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

                // 약국 데이터를 지도에 표시
                Storage_pharmacy.INSTANCE.getPharmacyList(Integer.parseInt(itemSeq), pharmacies -> {
                    if (pharmacies != null) {
                        for (Pharmacy pharmacy : pharmacies) {
                            // 재고량 텍스트 설정
                            String stockInfo = String.valueOf(pharmacy.getRemain());

                            // 약국 이름을 텍스트로 설정
                            String pharmacyName = pharmacy.getPharm().getName();

                            // 커스텀 마커 생성
                            Bitmap customMarker = createCustomMarker(stockInfo);

                            // 약국 이름을 마커 아래에 추가하는 텍스트
                            LabelTextBuilder labelTextBuilder = new LabelTextBuilder();
                            labelTextBuilder.setTexts(pharmacyName); // 약국 이름을 텍스트로 설정

                            // 커스텀 스타일로 마커 설정
                            LabelStyle customStyle = LabelStyle.from(customMarker)
                                    .setTextStyles(LabelTextStyle.from(40, Color.BLACK, 2, Color.GRAY));

                            // LabelOptions 설정
                            LabelOptions options = LabelOptions.from(LatLng.from(pharmacy.getPharm().getLat(), pharmacy.getPharm().getLon()))
                                    .setTexts(labelTextBuilder)  // 텍스트에 약국 이름 추가
                                    .setStyles(customStyle);

                            // 레이블 추가
                            Label label = kakaoMap.getLabelManager().getLayer().addLabel(options);
                            labelPharmacyMap.put(label, pharmacy);
                        }

                        // 레이블 클릭 이벤트 처리
                        kakaoMap.setOnLabelClickListener((kakaoMap, labelLayer, clickedLabel) -> {
                            if (labelPharmacyMap.containsKey(clickedLabel)) {
                                Pharmacy pharmacy = labelPharmacyMap.get(clickedLabel);

                                // Bottom Card 표시 및 업데이트
                                updateBottomCard(pharmacy, bottomCard);

                                return true;
                            }
                            return false;
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
     * Bottom Card 업데이트
     */
    private void updateBottomCard(Pharmacy pharmacy, View bottomCard) {
        bottomCard.setVisibility(View.VISIBLE);

        TextView textPlaceName = bottomCard.findViewById(R.id.text_place_name);
        TextView textPlaceType = bottomCard.findViewById(R.id.text_place_type);
        TextView textPlaceStatus = bottomCard.findViewById(R.id.text_place_status);
        TextView textPlaceDistance = bottomCard.findViewById(R.id.text_place_distance);

        textPlaceName.setText(pharmacy.getPharm().getName());
        textPlaceType.setText("Pharmacy");
        textPlaceStatus.setText(pharmacy.getId());
        textPlaceDistance.setText("39m");

        Button navigateButton = bottomCard.findViewById(R.id.button_navigate);
        Button directionsButton = bottomCard.findViewById(R.id.button_directions);

        navigateButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:" + pharmacy.getPharm().getLat() + "," + pharmacy.getPharm().getLon()));
            startActivity(intent);
        });

        directionsButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("google.navigation:q=" + pharmacy.getPharm().getLat() + "," + pharmacy.getPharm().getLon()));
            startActivity(intent);
        });
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
     * 약국 정보를 표시하는 InfoWindow 생성
     */
    private void showPharmacyInfo(Pharmacy pharmacy) {
        if (getActivity() == null || isDetached()) {
            Log.e("InfoWindow", "Activity가 종료되었거나 Fragment가 분리되었습니다.");
            return; // InfoWindow를 생성하지 않음
        }

        GuiLayout body = new GuiLayout(Orientation.Vertical);
        body.setPadding(20, 20, 20, 20);

        GuiImage bgImage = new GuiImage(R.drawable.window_body, true);
        bgImage.setFixedArea(7, 7, 7, 7);
        body.setBackground(bgImage);

        GuiText pharmacyNameText = new GuiText(pharmacy.getPharm().getName());
        pharmacyNameText.setTextSize(24);
        pharmacyNameText.setTextColor(Color.BLACK);
        body.addView(pharmacyNameText);

        GuiText stockText = new GuiText("재고 수량: " + pharmacy.getRemain());
        stockText.setTextSize(18);
        stockText.setTextColor(Color.DKGRAY);
        body.addView(stockText);

        InfoWindowOptions options = InfoWindowOptions.from(LatLng.from(
                pharmacy.getPharm().getLat(),
                pharmacy.getPharm().getLon()
        ));
        options.setBody(body);
        options.setBodyOffset(0, -20);
        options.setTail(new GuiImage(R.drawable.window_tail, false));

        if (kakaoMap != null) {
            kakaoMap.getMapWidgetManager().getInfoWindowLayer().addInfoWindow(options);
        } else {
            Log.e("InfoWindow", "KakaoMap 객체가 null입니다.");
        }
    }




    private void showBottomCard(View card) {
        card.setVisibility(View.VISIBLE);
        card.setTranslationY(card.getHeight());
        card.animate().translationY(0).setDuration(300).start();
    }

    private void hideBottomCard(View card) {
        card.animate().translationY(card.getHeight()).setDuration(300)
                .withEndAction(() -> card.setVisibility(View.GONE)).start();
    }





    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // View Binding 해제
    }
}
