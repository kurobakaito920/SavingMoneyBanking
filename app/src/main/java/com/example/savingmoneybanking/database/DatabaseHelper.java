package com.example.savingmoneybanking.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.savingmoneybanking.model.SavingBanking;
import com.example.savingmoneybanking.model.Wallet;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    DatabaseSaveMoneyBank dbSMB;
    SQLiteDatabase db;
    public DatabaseHelper(Context context){
        dbSMB = new DatabaseSaveMoneyBank(context);
        db = dbSMB.getWritableDatabase();
    }

    public void insertUser(String email, String username, String password){
        ContentValues values = new ContentValues();

        values.put(DatabaseCreateTable.COL_EMAIL_USER, email);
        values.put(DatabaseCreateTable.COL_NAME_USER, username);
        values.put(DatabaseCreateTable.COL_PASS_USER, password);

        db.insert(DatabaseCreateTable.TABLE_USER, null, values);
        db.close();
    }
    public Boolean checkEmail(String email){
        db = dbSMB.getReadableDatabase();
        String sql = "SELECT * FROM " + DatabaseCreateTable.TABLE_USER + " WHERE " + DatabaseCreateTable.COL_EMAIL_USER + " = ? ";
        Cursor cursor = db.rawQuery(sql, new String[]{email});
        if(cursor.getCount()>0){
            return false;
        }
        else{
            return true;
        }
    }
    public Boolean checkAccount(String email, String password){
        db = dbSMB.getReadableDatabase();
        String sql = "SELECT * FROM " + DatabaseCreateTable.TABLE_USER + " WHERE " + DatabaseCreateTable.COL_EMAIL_USER + " = ? " + " AND " + DatabaseCreateTable.COL_PASS_USER + " = ? ";
        Cursor cursor = db.rawQuery(sql, new String[]{email, password});
        if(cursor.getCount()>0){
            return true;
        } else {
            return false;
        }
    }

    public void insertWallet(String namewallet, String prices){
        ContentValues values = new ContentValues();

        values.put(DatabaseCreateTable.COL_NAME_WALLET, namewallet);
        values.put(DatabaseCreateTable.COL_PRICES_WALLET, prices);

        db.insert(DatabaseCreateTable.TABLE_WALLET, null, values);
        db.close();
    }
    public void deleteWallet(int id_wallet){
        String sql = "DELETE FROM " + DatabaseCreateTable.TABLE_WALLET + " WHERE " + DatabaseCreateTable.COL_ID_WALLET + " = " + id_wallet;
        db.execSQL(sql);
    }

    public void updateWallet(int id_wallet, String namewallet, String prices){
        ContentValues values = new ContentValues();

        values.put(DatabaseCreateTable.COL_NAME_WALLET, namewallet);
        values.put(DatabaseCreateTable.COL_PRICES_WALLET, prices);
        db.update(DatabaseCreateTable.TABLE_WALLET, values, DatabaseCreateTable.COL_ID_WALLET, new String[]{String.valueOf(id_wallet)});
        db.close();
    }

    public void changeMoney(String namewallet, String prices){
        db = dbSMB.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseCreateTable.COL_PRICES_WALLET, prices);
        db.update(DatabaseCreateTable.TABLE_WALLET, values, DatabaseCreateTable.COL_NAME_WALLET + " =?", new String[]{namewallet});
    }
    public String getPrices(String namewallet){
        db = dbSMB.getReadableDatabase();
        String[] prices = {DatabaseCreateTable.COL_PRICES_WALLET};
        Cursor cursor = db.query(DatabaseCreateTable.TABLE_WALLET, prices, DatabaseCreateTable.COL_NAME_WALLET + " =?", new String[]{namewallet},null,null,null);
        String price = "";
        while (cursor.moveToNext()){
            price = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseCreateTable.COL_PRICES_WALLET));
        }
        cursor.close();
        db.close();
        return price;
    }
    public String getWalletName(){
        db = dbSMB.getReadableDatabase();
        String[] name = {DatabaseCreateTable.COL_NAME_WALLET};
        Cursor cursor = db.query(DatabaseCreateTable.TABLE_WALLET, name, null, null,null,null,null);
        String nameWallet = "";
        while (cursor.moveToNext()){
            nameWallet = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseCreateTable.COL_NAME_WALLET));
        }
        cursor.close();
        db.close();
        return nameWallet;
    }

    public List<Wallet> getWallet(){
        List<Wallet> listWallet = new ArrayList<>();
        String sql = "SELECT * FROM " + DatabaseCreateTable.TABLE_WALLET;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do {
                int id_wallet = cursor.getInt(0);
                String namewallet = cursor.getString(1);
                String prices = cursor.getString(2);
                Wallet wallet = new Wallet(id_wallet, namewallet, prices);
                listWallet.add(wallet);
            }while (cursor.moveToNext());
        }
        return listWallet;
    }

    public Wallet getAllWallet(int id){
        Wallet wallet = null;
        String sql = "SELECT * FROM " + DatabaseCreateTable.TABLE_WALLET + " WHERE " + DatabaseCreateTable.COL_ID_WALLET + " = " + id;
        Cursor cursor =db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do {
                int id_wallet = cursor.getInt(0);
                String namewallet = cursor.getString(1);
                String prices = cursor.getString(2);
                wallet = new Wallet(id_wallet, namewallet, prices);
            } while (cursor.moveToNext());
        }
        return wallet;
    }

    public List<String> getAllNameWallet(){
        List<String> list = new ArrayList<String>();
        String sql = "SELECT * FROM " + DatabaseCreateTable.TABLE_WALLET;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                list.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
    public List<String> getAllSendingTerm(){
        db = dbSMB.getWritableDatabase();
        List<String> listSendingTerm = new ArrayList<String>();
        String sql = "SELECT * FROM " + DatabaseCreateTable.TABLE_SEDNING_TERM;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                listSendingTerm.add(cursor.getString(1));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listSendingTerm;
    }
    public String getPercentage(String monthsending){
        db = dbSMB.getReadableDatabase();
        String[] percentage = {DatabaseCreateTable.COL_PERCENTAGE};
        Cursor cursor = db.query(DatabaseCreateTable.TABLE_SEDNING_TERM, percentage, DatabaseCreateTable.COL_MONTH_SENDING + " =?", new String[]{monthsending},null,null,null);
        String percentageMonth = "";
        while (cursor.moveToNext()){
            percentageMonth = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseCreateTable.COL_PERCENTAGE));
        }
        cursor.close();
        db.close();
        return percentageMonth;
    }
    public List<String> getAllNameMethodPayment(){
        db = dbSMB.getWritableDatabase();
        List<String> listNameMethodPayment = new ArrayList<String>();
        String sql = "SELECT * FROM " + DatabaseCreateTable.TABLE_METHOD_INTEREST_PAYMENT;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                listNameMethodPayment.add(cursor.getString(1));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listNameMethodPayment;
    }
    public void insertSavingBank(String walletSavingBank, String monthSendingSavingBank, String percentOnMonthBank, String moneySendToBank, String methodPaymentBank){
        db = dbSMB.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseCreateTable.COL_WALLET_SAVING_BANKING, walletSavingBank);
        values.put(DatabaseCreateTable.COL_MONTH_SAVING_BANKING, monthSendingSavingBank);
        values.put(DatabaseCreateTable.COL_PERCENT_ONE_MONTH, percentOnMonthBank);
        values.put(DatabaseCreateTable.COL_MONEY_SEND_TO_BANK, moneySendToBank);
        values.put(DatabaseCreateTable.COL_METHOD_PAYMENT, methodPaymentBank);

        db.insert(DatabaseCreateTable.TABLE_SAVING_BANKING, null, values);
        db.close();
    }

    public void deleteSavingBank(int idSavingBank){
        String sql = "DELETE FROM " + DatabaseCreateTable.TABLE_SAVING_BANKING + " WHERE " + DatabaseCreateTable.COL_ID_SAVING_BANKING + " = " + idSavingBank;
        db.execSQL(sql);
    }

    public List<SavingBanking> getAllSavingBank(){
        List<SavingBanking> listSavingBank = new ArrayList<>();
        String sql = "SELECT * FROM " + DatabaseCreateTable.TABLE_SAVING_BANKING;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do {
                int idSavingBank = cursor.getInt(0);
                String walletSavingBank = cursor.getString(1);
                String monthSendingSavingBank = cursor.getString(2);
                String percentOnMonthBank = cursor.getString(3);
                String moneySendToBank = cursor.getString(4);
                String methodPaymentBank = cursor.getString(5);
                SavingBanking savingBanking = new SavingBanking(idSavingBank, walletSavingBank, monthSendingSavingBank, percentOnMonthBank, moneySendToBank, methodPaymentBank);
                listSavingBank.add(savingBanking);
            }while (cursor.moveToNext());
        }
        return listSavingBank;
    }
    public List<SavingBanking> fineOneSavingBank(String walletSavingBank){
        List<SavingBanking> listSavingBank = new ArrayList<>();
        String sql = "SELECT * FROM " + DatabaseCreateTable.TABLE_SAVING_BANKING + " WHERE " + DatabaseCreateTable.COL_WALLET_SAVING_BANKING + " =? ";
        Cursor cursor = db.rawQuery(sql, new String[]{walletSavingBank});
        if(cursor.moveToFirst()){
            do {
                int idSavingBank = cursor.getInt(0);
                String name = cursor.getString(1);
                String monthSendingSavingBank = cursor.getString(2);
                String percentOnMonthBank = cursor.getString(3);
                String moneySendToBank = cursor.getString(4);
                String methodPaymentBank = cursor.getString(5);
                SavingBanking savingBanking = new SavingBanking(idSavingBank, name, monthSendingSavingBank, percentOnMonthBank, moneySendToBank, methodPaymentBank);
                listSavingBank.add(savingBanking);
            }while (cursor.moveToNext());
        }
        return listSavingBank;
    }
}
