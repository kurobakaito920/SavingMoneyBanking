package com.example.savingmoneybanking.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.savingmoneybanking.MainActivity;
import com.example.savingmoneybanking.R;
import com.example.savingmoneybanking.database.DatabaseHelper;

import java.text.DecimalFormat;
import java.util.List;

public class SeeProfitActivity extends AppCompatActivity {
    private TextView txtPricesInWallet;
    private EditText edtMoneySend;
    private ImageView btnBack;
    private Button btnContinue;
    private List<String> listWallet, listSendingTerm, listMethodPayment ;
    private DatabaseHelper databaseHelper;
    private Spinner spinner, spinner2, spinner3;
    private String nameWallet, sendingTerm, getPricesWallet, pricesIn, nameMethodPayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_profit);
        databaseHelper = new DatabaseHelper(this);
        anhXa();
        loadSpinnerData();
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pricesIn = edtMoneySend.getText().toString();
                Intent i = new Intent(SeeProfitActivity.this, ConfirmActivity.class);
                i.putExtra("namewallet", nameWallet);
                i.putExtra("sendingTerm", sendingTerm);
                i.putExtra("moneySend", pricesIn);
                i.putExtra("nameMethodPayment", nameMethodPayment);
                startActivity(i);
                finish();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SeeProfitActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void anhXa() {
        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        txtPricesInWallet = findViewById(R.id.txtMoneyInWallet);
        btnContinue = findViewById(R.id.btnContinue);
        edtMoneySend = findViewById(R.id.edtTienGui);
        btnBack = findViewById(R.id.back_btn);
    }

    private void loadSpinnerData() {
        listWallet = databaseHelper.getAllNameWallet();
        listSendingTerm = databaseHelper.getAllSendingTerm();
        listMethodPayment = databaseHelper.getAllNameMethodPayment();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listWallet);
        ArrayAdapter<String> dataSendingAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listSendingTerm);
        ArrayAdapter<String> dataMethodPaymentAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listMethodPayment);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner2.setAdapter(dataSendingAdapter);
        spinner3.setAdapter(dataMethodPaymentAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nameWallet = adapterView.getItemAtPosition(i).toString();
                getPricesWallet = databaseHelper.getPrices(nameWallet);
                txtPricesInWallet.setText(currencyFormat(getPricesWallet) + " VND");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sendingTerm = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nameMethodPayment = adapterView.getItemAtPosition(i).toString();
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