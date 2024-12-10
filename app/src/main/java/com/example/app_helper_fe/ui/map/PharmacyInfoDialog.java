package com.example.app_helper_fe.ui.map;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.app_helper_fe.R;
import com.example.app_helper_fe.data.Pharmacy;

public class PharmacyInfoDialog extends Dialog {

    private Pharmacy pharmacy;

    public PharmacyInfoDialog(@NonNull Context context, Pharmacy pharmacy) {
        super(context);
        this.pharmacy = pharmacy;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_pharmacy_info);

        // UI 요소 초기화
        TextView nameTextView = findViewById(R.id.text_pharmacy_name);
        TextView addressTextView = findViewById(R.id.text_pharmacy_address);
        TextView phoneTextView = findViewById(R.id.text_pharmacy_phone);
        TextView medicationIds = findViewById(R.id.text_pharmacy_medication_ids);

        // 약국 데이터 설정
        nameTextView.setText(pharmacy.getPharm().getName());
        addressTextView.setText("주소: " + pharmacy.getPharm().getAddress());
        phoneTextView.setText("번호: " +pharmacy.getPharm().getPhone());
        medicationIds.setText("제고: "+ pharmacy.getRemain());

        Button closeButton = findViewById(R.id.button_close);
        closeButton.setOnClickListener(v -> dismiss());
    }
}

