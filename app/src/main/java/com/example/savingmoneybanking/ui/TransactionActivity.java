package com.example.savingmoneybanking.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.SearchView;

import com.example.savingmoneybanking.R;
import com.example.savingmoneybanking.adapter.TransactionAdapter;
import com.example.savingmoneybanking.database.DatabaseHelper;
import com.example.savingmoneybanking.model.SavingBanking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TransactionActivity extends AppCompatActivity {
    private List<SavingBanking> listSavingBank = new ArrayList<>();
    private DatabaseHelper databaseHelper;
    RecyclerView rvTransaction;
    TransactionAdapter transactionAdapter;
    SearchView searchTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        databaseHelper = new DatabaseHelper(this);
        anhXa();
        listSavingBank = databaseHelper.getAllSavingBank();
        if(listSavingBank.size() > 0){
            transactionAdapter = new TransactionAdapter(listSavingBank, this);
            rvTransaction.setAdapter(transactionAdapter);
            rvTransaction.setLayoutManager(new LinearLayoutManager(this));
        }
        searchTransaction.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                listSavingBank.clear();
                listSavingBank.addAll(databaseHelper.fineOneSavingBank(s));
                transactionAdapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    private void anhXa() {
        rvTransaction = findViewById(R.id.recyclerTransaction);
        searchTransaction = findViewById(R.id.searchTransaction);
    }
}