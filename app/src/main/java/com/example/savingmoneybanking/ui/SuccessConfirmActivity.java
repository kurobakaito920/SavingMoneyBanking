package com.example.savingmoneybanking.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.savingmoneybanking.R;

public class SuccessConfirmActivity extends AppCompatActivity {
    private Button btngdichmoi;
    private TextView txtResult, txtNameWallet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_confirm);
        anhXa();
        Intent intent = getIntent();
        txtResult.setText(intent.getStringExtra("moneySend"));
        txtNameWallet.setText(intent.getStringExtra("namewallet"));
        btngdichmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SuccessConfirmActivity.this, SeeProfitActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void anhXa() {
        btngdichmoi = (Button) findViewById(R.id.btnGdichMoi);
        txtResult = findViewById(R.id.tvTienTcong);
        txtNameWallet = findViewById(R.id.tvShowtkTkiem);
    }
}