package com.example.savingmoneybanking.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.savingmoneybanking.R;
import com.example.savingmoneybanking.database.DatabaseHelper;
import com.example.savingmoneybanking.model.Wallet;
import com.example.savingmoneybanking.ui.AddMoneyActivity;
import com.example.savingmoneybanking.ui.MyWalletActivity;
import com.example.savingmoneybanking.ui.SeeProfitActivity;
import com.example.savingmoneybanking.ui.WithdrawMoneyActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class HomeFragment extends Fragment implements SearchView.OnQueryTextListener{
    private Button btnAddWallet;
    private TextView txtMoney;
    private SearchView searchView;
    private RecyclerView rycView;
    private String value;
    private LinearLayout imgSpending, imgSendMoney, imgDonate;
    private SharedPreferences sharedPreferences;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //seachWalletAdapter = new SeachWalletAdapter(getContext(), arrayList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        anhXa(v);

        btnAddWallet.setOnClickListener(clickToAddWallet());
        imgSendMoney.setOnClickListener(clickToWithDrawMoney());
        imgDonate.setOnClickListener(clickToAddMoney());
        imgSpending.setOnClickListener(clickToAddSavingBank());

        searchView.setOnQueryTextListener(this);

        sharedPreferences = requireActivity().getSharedPreferences("item", Context.MODE_PRIVATE);
        onResume();
        return v;
    }

    private void anhXa(View v) {
        btnAddWallet = v.findViewById(R.id.btnAdd);
        txtMoney = v.findViewById(R.id.txtMoney);
        imgSendMoney = v.findViewById(R.id.send_money);
        imgDonate = v.findViewById(R.id.donate);
        imgSpending = v.findViewById(R.id.spending);
        searchView = v.findViewById(R.id.search);
        rycView = v.findViewById(R.id.searchViewRecycler);
    }
    @NonNull
    private View.OnClickListener clickToAddWallet() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MyWalletActivity.class);
                startActivity(i);
                onPause();
            }
        };
    }
    @NonNull
    private View.OnClickListener clickToWithDrawMoney() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), WithdrawMoneyActivity.class);
                startActivity(i);
            }
        };
    }
    @NonNull
    private View.OnClickListener clickToAddMoney() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AddMoneyActivity.class);
                startActivity(i);
            }
        };
    }
    @NonNull
    private View.OnClickListener clickToAddSavingBank() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), SeeProfitActivity.class);
                startActivity(i);
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        value = sharedPreferences.getString("prices", "0");
        txtMoney.setText(currencyFormat(value) + " VND");
    }
    public String currencyFormat(String prices){
        double m = Double.parseDouble(prices);
        DecimalFormat format = new DecimalFormat("###,###,###");
        return format.format(m);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if(s.isEmpty()){
            rycView.setVisibility(View.GONE);
        } else {
            rycView.setVisibility(View.VISIBLE);
        }
        return false;
    }
}