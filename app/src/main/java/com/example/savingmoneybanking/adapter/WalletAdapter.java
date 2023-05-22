package com.example.savingmoneybanking.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.savingmoneybanking.MainActivity;
import com.example.savingmoneybanking.R;
import com.example.savingmoneybanking.database.DatabaseHelper;
import com.example.savingmoneybanking.fragment.HomeFragment;
import com.example.savingmoneybanking.model.Wallet;
import com.example.savingmoneybanking.ui.MyWalletActivity;

import java.text.DecimalFormat;
import java.util.List;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.ViewHolder> {
    private List<Wallet> listWallet;
    private Context context;
    private DatabaseHelper databaseHelper;

    public WalletAdapter(List<Wallet> listWallet, Context context) {
        this.listWallet = listWallet;
        this.context = context;
        this.databaseHelper = new DatabaseHelper(context);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.wallet_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WalletAdapter.ViewHolder holder, int position) {
        Wallet wallet = listWallet.get(position);
        holder.txtNameWallet.setText("Tên ví: " + wallet.getNamewallet());
        holder.txtPricesWallet.setText("Số tiền trong ví: " + currencyFormat(wallet.getPrices()) + " VND");
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.deleteWallet(wallet.getId_wallet());
                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                listWallet.remove(wallet);
                notifyDataSetChanged();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("item", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("prices", wallet.getPrices());
                editor.apply();
                Toast.makeText(context, "Chọn thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listWallet.size();
    }

    public   class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNameWallet, txtPricesWallet;
        private ImageButton btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameWallet = itemView.findViewById(R.id.nameWallet);
            txtPricesWallet = itemView.findViewById(R.id.pricesInWallet);
            btnDelete = itemView.findViewById(R.id.icDelete);
        }
    }
    public String currencyFormat(String prices){
        double m = Double.parseDouble(prices);
        DecimalFormat format = new DecimalFormat("###,###,###");
        return format.format(m);
    }
}
