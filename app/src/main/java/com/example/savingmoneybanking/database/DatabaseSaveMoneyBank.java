package com.example.savingmoneybanking.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseSaveMoneyBank extends SQLiteOpenHelper {
    public DatabaseSaveMoneyBank(@Nullable Context context) {
        super(context, DatabaseCreateTable.DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DatabaseCreateTable.CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(DatabaseCreateTable.CREATE_TABLE_WALLET);
        sqLiteDatabase.execSQL(DatabaseCreateTable.CREATE_TABLE_SENDING_TERM);
        sqLiteDatabase.execSQL(DatabaseCreateTable.CREATE_TABLE_METHOD_INTERES_PAYMENT);
        sqLiteDatabase.execSQL(DatabaseCreateTable.CREATE_TABLE_SAVING_BANK);
        sqLiteDatabase.execSQL("insert into user values(null, 'abc@gmail.com', 'abc', '123456') ");
        sqLiteDatabase.execSQL("insert into wallet values(null, 'abc', '1000000', null) ");
        sqLiteDatabase.execSQL("insert into sending_term values(null, '14 ngày', '0.2%/Năm')");
        sqLiteDatabase.execSQL("insert into sending_term values(null, '1 tháng', '5.5%/Năm')");
        sqLiteDatabase.execSQL("insert into sending_term values(null, '3 tháng', '5.5%/Năm')");
        sqLiteDatabase.execSQL("insert into sending_term values(null, '6 tháng', '6.5%/Năm')");
        sqLiteDatabase.execSQL("insert into sending_term values(null, '9 tháng', '6.5%/Năm')");
        sqLiteDatabase.execSQL("insert into sending_term values(null, '12 tháng', '7.4%/Năm')");
        sqLiteDatabase.execSQL("insert into sending_term values(null, '24 tháng', '7.2%/Năm')");
        sqLiteDatabase.execSQL("insert into methodInterest_Payment values(null, 'Lãi nhập gốc')");
        sqLiteDatabase.execSQL("insert into methodInterest_Payment values(null, 'Lãi trả vào tài khoản tiền gửi khi đến hạn trả lãi')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DatabaseCreateTable.DROP_TABLE_USER);
        sqLiteDatabase.execSQL(DatabaseCreateTable.DROP_TABLE_WALLET);
        sqLiteDatabase.execSQL(DatabaseCreateTable.DROP_TABLE_SENDING_TERM);
        sqLiteDatabase.execSQL(DatabaseCreateTable.DROP_TABLE_METHOD_INTERES_PAYMENT);
        sqLiteDatabase.execSQL(DatabaseCreateTable.DROP_TABLE_SAVING_BANING);
    }
}
