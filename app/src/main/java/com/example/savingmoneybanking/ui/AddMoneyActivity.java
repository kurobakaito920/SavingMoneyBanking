package com.example.savingmoneybanking.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.savingmoneybanking.R;
import com.example.savingmoneybanking.database.DatabaseHelper;

import java.text.DecimalFormat;
import java.util.List;

public class AddMoneyActivity extends AppCompatActivity{
    private TextView walletBalance, walletAddMoney;
    private EditText edtAddMoey;
    private Button btnAddMoney;
    private Spinner spinner;
    private DatabaseHelper databaseHelper;
    private List<String> listWallet;
    private String nameWallet, getPricesWallet, pricesIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);
        databaseHelper = new DatabaseHelper(this);
        anhXa();
        loadSpinnerData();
        btnAddMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pricesIn = edtAddMoey.getText().toString();
                Integer pricesInAddMoney = Integer.parseInt(pricesIn);
                Integer getPricesAdd = Integer.parseInt(getPricesWallet);
                Integer pricesAdd = pricesInAddMoney + getPricesAdd;
                String updatePricesAdd = pricesAdd.toString();
                databaseHelper.changeMoney(nameWallet, updatePricesAdd);
                walletAddMoney.setText(currencyFormat(pricesIn) + " VND");
                finish();
            }
        });
    }

    private void anhXa() {
        walletBalance = findViewById(R.id.tvHienSoDu);
        spinner = findViewById(R.id.spinnerAccount);
        edtAddMoey = findViewById(R.id.edtNap);
        btnAddMoney = findViewById(R.id.btnNap);
        walletAddMoney = findViewById(R.id.tvTienNap);
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
                getPricesWallet = databaseHelper.getPrices(nameWallet);
                walletBalance.setText(currencyFormat(getPricesWallet) + " VND");
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