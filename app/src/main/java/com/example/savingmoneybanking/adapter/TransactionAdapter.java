package com.example.savingmoneybanking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.savingmoneybanking.R;
import com.example.savingmoneybanking.database.DatabaseHelper;
import com.example.savingmoneybanking.model.SavingBanking;

import java.text.DecimalFormat;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder>{
    private List<SavingBanking> listTransaction;
    private Context context;
    private DatabaseHelper databaseHelper;

    public TransactionAdapter(List<SavingBanking> listTransaction, Context context) {
        this.listTransaction = listTransaction;
        this.context = context;
        this.databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.transaction_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SavingBanking savingBanking = listTransaction.get(position);
        holder.txtNameInBank.setText("Tên ví: " + savingBanking.getWalletSavingBank());
        holder.txtPricesSendBank.setText("Tiền gửi vào sổ: " + savingBanking.getMoneySendToBank() + " VND");
        holder.txtMonthSend.setText("Tháng gửi: " + savingBanking.getMonthSendingSavingBank());
        holder.txtPercentBank.setText("Lãi suất: " + savingBanking.getPercentOnMonthBank());
        holder.txtMethodPayment.setText("Phương thức trả lãi: " + savingBanking.getMethodPaymentBank());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.deleteSavingBank(savingBanking.getIdSavingBank());
                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                listTransaction.remove(savingBanking);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTransaction.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNameInBank, txtPricesSendBank, txtMonthSend, txtPercentBank, txtMethodPayment;
        private ImageButton btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameInBank = itemView.findViewById(R.id.nameWalletTransaction);
            txtPricesSendBank = itemView.findViewById(R.id.pricesSendBank);
            txtMonthSend = itemView.findViewById(R.id.monthSendBank);
            txtPercentBank = itemView.findViewById(R.id.percentBank);
            txtMethodPayment = itemView.findViewById(R.id.methodBank);
            btnDelete = itemView.findViewById(R.id.icDeleteSavingBank);
        }
    }
    /*public String currencyFormat(String prices){
        double m = Double.parseDouble(prices);
        DecimalFormat format = new DecimalFormat("###,###,###");
        return format.format(m);
    }*/
}
