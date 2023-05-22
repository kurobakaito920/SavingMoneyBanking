package com.example.savingmoneybanking.database;

public class DatabaseCreateTable {
    //create database
    public static final String DATABASE_NAME = "SavingMoneyBanking.db";

    /* ------------- init value user to create table user ---------------- */
    public static final String TABLE_USER = "user";
    public static final String COL_ID_USER = "id_user";
    public static final String COL_EMAIL_USER = "email";
    public static final String COL_NAME_USER = "nameuser";
    public static final String COL_PASS_USER = "password";

    /* --------------- init value wallet to create table wallet ---------------- */
    public static final String TABLE_WALLET = "wallet";
    public static final String COL_ID_WALLET = "id_wallet";
    public static final String COL_NAME_WALLET = "namewallet";
    public static final String COL_PRICES_WALLET = "prices";

    /* ---------------- INIT VALUE SENDING_TERM TO CREATE TABLE SENDING_TERM -------------------- */
    public static final String TABLE_SEDNING_TERM = "sending_term";
    public static final String COL_ID_SENDING_TERM = "idSendingterm";
    public static final String COL_MONTH_SENDING = "monthsending";
    public static final String COL_PERCENTAGE = "percentage";

    /* ------------------ INIT VALUE METHOD_INTEREST_PAYMENT TO CREATE TABLE METHOD_INTEREST_PAYMENT ---------------*/
    public static final String TABLE_METHOD_INTEREST_PAYMENT = "methodInterest_Payment";
    public static final String COL_ID_METHOD_INTEREST = "id_method_interest";
    public static final String COL_NAME_METHOD_PAYMENT = "name_method_payment";


    /* ------------------ INIT VALUE SAVING_BANKING TO CREATE TABLE SAVING_BANKING ---------------- */
    public static final String TABLE_SAVING_BANKING = "saving_banking";
    public static final String COL_ID_SAVING_BANKING = "idSavingBank";
    public static final String COL_WALLET_SAVING_BANKING = "walletSavingBank";
    public static final String COL_MONTH_SAVING_BANKING = "monthSendingSavingBank";
    public static final String COL_PERCENT_ONE_MONTH = "percentOnMonthBank";
    public static final String COL_MONEY_SEND_TO_BANK = "moneySendToBank";
    public static final String COL_METHOD_PAYMENT = "methodPaymentBank";


    //CREATE TABLE USER
    public static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + " ("
            + COL_ID_USER + " INTEGER PRIMARY KEY, "
            + COL_EMAIL_USER + " TEXT, "
            + COL_NAME_USER + " TEXT, "
            + COL_PASS_USER + " TEXT) ";

    //CREATE TABLE WALLET
    public static  final String CREATE_TABLE_WALLET = "CREATE TABLE " + TABLE_WALLET + " ("
            + COL_ID_WALLET + " INTEGER PRIMARY KEY, "
            + COL_NAME_WALLET + " TEXT, "
            + COL_PRICES_WALLET + " TEXT, "
            + COL_ID_USER + " INTEGER)";

    //CREATE TABLE SENDING_TERM
    public static final String CREATE_TABLE_SENDING_TERM = "CREATE TABLE " + TABLE_SEDNING_TERM + " ("
            + COL_ID_SENDING_TERM + " INTEGER PRIMARY KEY, "
            + COL_MONTH_SENDING + " TEXT, "
            + COL_PERCENTAGE + " TEXT)";

    //CREATE TABLE METHOD_INTERES_PAYMENT
    public static final String CREATE_TABLE_METHOD_INTERES_PAYMENT = "CREATE TABLE " + TABLE_METHOD_INTEREST_PAYMENT + " ("
            + COL_ID_METHOD_INTEREST + " INTEGER PRIMARY KEY, "
            + COL_NAME_METHOD_PAYMENT + " TEXT)";

    //CREATE TABLE SAVING_BANKING
    public static final String CREATE_TABLE_SAVING_BANK = "CREATE TABLE " + TABLE_SAVING_BANKING + " ("
            + COL_ID_SAVING_BANKING + " INTEGER PRIMARY KEY, "
            + COL_WALLET_SAVING_BANKING + " TEXT, "
            + COL_MONTH_SAVING_BANKING + " TEXT, "
            + COL_PERCENT_ONE_MONTH + " TEXT, "
            + COL_MONEY_SEND_TO_BANK + " TEXT, "
            + COL_METHOD_PAYMENT + " TEXT)";

    public static final String DROP_TABLE_USER = "DROP TABLE IF EXISTS " + TABLE_USER;
    public static final String DROP_TABLE_WALLET = "DROP TABLE IF EXISTS " + TABLE_WALLET;
    public static final String DROP_TABLE_SENDING_TERM = "DROP TABLE IF EXISTS " + TABLE_SEDNING_TERM;
    public static final String DROP_TABLE_METHOD_INTERES_PAYMENT = "DROP TABLE IF EXISTS " + TABLE_METHOD_INTEREST_PAYMENT;
    public static final String DROP_TABLE_SAVING_BANING = "DROP TABLE IF EXISTS " + TABLE_SAVING_BANKING;
}
