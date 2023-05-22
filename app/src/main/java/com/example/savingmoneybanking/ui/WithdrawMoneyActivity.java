package com.example.savingmoneybanking.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.savingmoneybanking.R;
import com.example.savingmoneybanking.adapter.WalletAdapter;
import com.example.savingmoneybanking.database.DatabaseCreateTable;
import com.example.savingmoneybanking.database.DatabaseHelper;
import com.example.savingmoneybanking.model.Wallet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class WithdrawMoneyActivity extends AppCompatActivity{
    private EditText edtRutTien;
    private Button btnRut;
    private Spinner spinner;
    private DatabaseHelper databaseHelper;
    private List<String> listWallet;
    private String nameWallet, pricesIn, getPrices, updatePrices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_money);
        databaseHelper = new DatabaseHelper(this);
        anhXa();
        loadSpinnerData();
        btnRut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pricesIn = edtRutTien.getText().toString();
                Integer prices = Integer.parseInt(pricesIn);
                getPrices = databaseHelper.getPrices(nameWallet);
                Integer getPricesInWallet = Integer.parseInt(getPrices);
                Integer pricesOut = getPricesInWallet - prices;
                updatePrices = pricesOut.toString();
                if(prices > getPricesInWallet){
                    edtRutTien.setError("Vui lòng nhập số tiền rút nhỏ hơn số tiền trong ví");
                }else{
                    databaseHelper.changeMoney(nameWallet, updatePrices);
                    Toast.makeText(WithdrawMoneyActivity.this, "Đã rút: " + currencyFormat(pricesIn), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void anhXa(){
        edtRutTien = findViewById(R.id.edtNhapTien);
        btnRut = findViewById(R.id.btnRut);
        spinner = findViewById(R.id.ChonTaiKhoan);
    }
    private void loadSpinnerData() {
        listWallet = databaseHelper.getAllNameWallet();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listWallet);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nameWallet = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(WithdrawMoneyActivity.this, "Đã chọn: " + nameWallet, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    public String currencyFormat(String prices){
        double m = Double.parseDouble(prices);
        DecimalFormat format = new DecimalFormat("###,###,###");
        return format.format(m);
    }
}