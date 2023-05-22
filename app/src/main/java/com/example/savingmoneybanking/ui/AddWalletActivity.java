package com.example.savingmoneybanking.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.savingmoneybanking.R;
import com.example.savingmoneybanking.database.DatabaseHelper;

public class AddWalletActivity extends AppCompatActivity {
    private Button btnConfirm;
    private EditText edtNameWallet, edtPricesInWallet;
    private int id = -1;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wallet);
        databaseHelper = new DatabaseHelper(this);
        anhXa();
        btnConfirm.setOnClickListener(clickToAddWallet());
    }

    private void anhXa() {
        edtNameWallet = findViewById(R.id.edtNameWallet);
        edtPricesInWallet = findViewById(R.id.edtPricesWallet);
        btnConfirm = findViewById(R.id.btnCofirm);
    }

    @NonNull
    private View.OnClickListener clickToAddWallet() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namewallet = edtNameWallet.getText().toString();
                String prices = edtPricesInWallet.getText().toString();
                Boolean isValid = checkWallet(namewallet, prices);
                if(isValid){
                    if(id != -1){
                        Toast.makeText(AddWalletActivity.this, "Không thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        databaseHelper.insertWallet(namewallet, prices);
                        Toast.makeText(AddWalletActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(AddWalletActivity.this, MyWalletActivity.class);
                        startActivity(i);
                        finish();
                    }
                }
            }
        };
    }

    boolean checkWallet(String nameWallet, String prices){
        if(nameWallet.isEmpty()){
            edtNameWallet.setError("Vui lòng không để trống");
            return false;
        }
        if(prices.isEmpty()){
            edtPricesInWallet.setError("Vui lòng không để trống");
            return false;
        }
        return true;
    }
}